package com.example.defmess.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainViewModel extends ViewModel {

    private MutableLiveData<JSONArray> pubDefMessages = null;

//    public MainViewModel() {
//        pubDefMessages = new MutableLiveData<>();
////        pubDefMessages.setValue();
//    }
    public void savePubDefMessages(JSONArray jsonArray){
        pubDefMessages = new MutableLiveData<JSONArray>(jsonArray);
    }

    public LiveData<JSONArray> getPubDefMessages() {
        return pubDefMessages;
    }

    public Boolean isSaved() {
        return pubDefMessages != null;
    }
}

