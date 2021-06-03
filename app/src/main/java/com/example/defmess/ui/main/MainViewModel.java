package com.example.defmess.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.defmess.DateUtils;
import com.example.defmess.JsonFilesManager;
import com.example.defmess.RequestToServer;
import com.example.defmess.ui.defmess.DefMess;
import com.example.defmess.ui.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends ViewModel {
    private MutableLiveData<JSONArray> pub_def_messages;
    //    private MutableLiveData<JSONObject> jsonData = new MutableLiveData<JSONObject>();
    private MutableLiveData<String> jwt_code;
    private MutableLiveData<User> user;
    private MutableLiveData<JSONArray> userDefMessages;

    private MutableLiveData<Boolean> currDefMessId;

//    private SavedStateHandle state;


    public MainViewModel(SavedStateHandle stateHandle) throws JSONException {
//        state = stateHandle;

//        jwt_code = stateHandle.getLiveData("jwt_code");
//        user = stateHandle.getLiveData("user") ? getUser();
//        userDefMessages = stateHandle.getLiveData("userDefMessages");
//        pub_def_messages = stateHandle.getLiveData("pub_def_messages");

//        if (jwt_code.getValue() == null){
////            logout();
//        }

    }

    public String getJwt_code() throws JSONException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("jwt_code", jwt_code);
        return jwt_code.getValue();
    }

    public void setJwt_code(String jwt_code) {
        this.jwt_code = new MutableLiveData<>();
        this.jwt_code.setValue(jwt_code);
    }

    public Boolean isLogin() {
//        Log.e("isLogin", "q" + jwt_code.getValue());
        return jwt_code.getValue() != null;
    }

    public void logout() {
        jwt_code.setValue(null);
//        user.setValue(null);
//        jsonData.getValue().remove("jwt_code");
    }

    public Boolean login(String email, String password) throws JSONException, InterruptedException, ExecutionException, IOException {
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
        JSONObject jsonResponse = request.post("/user/login", jsonObject.toString());
        if (jsonResponse != null) {
            jwt_code = new MutableLiveData<>();
            jwt_code.setValue(jsonResponse.getString("jwt_code"));
//            addToJsonData("jwt_code", jsonResponse.getString("jwt_code"));
        } else {
            return false;
        }
        getProfile();
        return true;
    }

    public Boolean register(String name, String surname, String email, String password) throws JSONException, InterruptedException, ExecutionException, IOException {
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("name", name);
        jsonRequest.put("surname", surname);
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
        JSONObject jsonResponse = request.post("/user/register", jsonRequest.toString());
        if (jsonResponse != null) {
            return false;
        }
        return true;
    }

    public LiveData<JSONArray> getProfile() throws JSONException, InterruptedException, ExecutionException, IOException {
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
//        JsonFilesManager filesManager = new JsonFilesManager(getContext().getApplicationContext());
        JSONObject jsonUserProfile = request.post("/user/profile",
                new JSONObject().put("jwt_code", jwt_code.getValue()).toString())
                .getJSONObject("user");
        user = new MutableLiveData<>();
        user.setValue(new User(jsonUserProfile));
        userDefMessages = new MutableLiveData<>();
        userDefMessages.setValue(jsonUserProfile.getJSONArray("def_messages"));
        return userDefMessages;
    }

    public Bitmap getUserAvatar(){
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        try {
            Bitmap bitmapResponse = request.getImage(user.getValue().link_to_photo);
            if (bitmapResponse != null) {
                return bitmapResponse;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<User> getUser() {
        if (user == null || user.getValue() == null) {
            try {
                getProfile();
            } catch (JSONException | InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public LiveData<JSONArray> getPubDefMessages() throws JSONException, ExecutionException, InterruptedException {
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        JSONObject jsonResponse = request.get("/defmess/publicated");
        if (jsonResponse != null) {
            pub_def_messages = new MutableLiveData<>();
            pub_def_messages.setValue(jsonResponse.getJSONArray("pub_def_messages"));
        }
        return pub_def_messages;
    }

    public Boolean createDefMess(DefMess defMess) throws InterruptedException, ExecutionException, JSONException, IOException {
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        DateUtils dateUtils = new DateUtils();
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("jwt_code", jwt_code.getValue());

        jsonRequest.put("appearance", defMess.appearance);
        jsonRequest.put("description", defMess.description);
        jsonRequest.put("location", defMess.location);

        jsonRequest.put("begin_date", defMess.begin_date);
        jsonRequest.put("end_date", defMess.end_date);
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
        JSONObject jsonResponse = request.post("/defmess/create", jsonRequest.toString());
        if (jsonResponse != null) {
            return true;
        }
        return false;
    }

//    private Boolean checkResponse(JSONObject jsonResponse, Context context) throws JSONException {
//        if (jsonResponse.has("error")) {
//            String error;
//            Toast.makeText(context, jsonResponse.getString("error"), Toast.LENGTH_LONG).show();
//            return false;
////            switch (jsonResponse.getString("error")) {
////                case "Empty request": {
////                    error = "Empty request";
////                } break;
////
////                case "Missing key": {
////                    error = "Missing key";
////                } break;
////
////                case "Incorrect key": {
////                    error = "Incorrect key";
////                } break;
////
////                case "Bad request": {
////                    error = "Bad request";
////                } break;
////            }
//        }
//        return true;
//    }
    /*Empty request
Missing key
Incorrect key
Bad request*/


//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        state.set("jwt_code", jwt_code.getValue());
//        state.set("user", user.getValue());
//        state.set("userDefMessages", userDefMessages.getValue());
//        state.set("pub_def_messages", pub_def_messages.getValue());
//    }
}