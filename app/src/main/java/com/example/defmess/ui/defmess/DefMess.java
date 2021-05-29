package com.example.defmess.ui.defmess;


import android.util.Log;

import com.example.defmess.ui.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DefMess {
    public Integer id;
    public String appearance;
    public String begin_date;
    public String end_date;
    public String location;
    public String description;
    public Boolean is_published;
    public User user;

    public DefMess(JSONObject jsonDefMess) throws JSONException {
//        for (String name : new String[]{"id", "appearance", "begin_date",
//                "end_date", "location", "description", "is_published"}) {
//            jsonObject.get(name);
//        }
//        JSONObject jsonDefMess = jsonObject.getJSONObject("defmess");
//        Log.e("JSONObject to DefMess", jsonObject.toString());
        id = jsonDefMess.getInt("id");
        appearance = jsonDefMess.getString("appearance");
        begin_date = jsonDefMess.getString("begin_date");
        end_date = jsonDefMess.getString("end_date");
        location = jsonDefMess.getString("location");
        description = jsonDefMess.getString("description");
        is_published = jsonDefMess.getBoolean("is_published");
        user = new User(jsonDefMess.optJSONObject("user"));
    }

    public DefMess(JSONObject jsonDefMess, User user) throws JSONException {
        id = jsonDefMess.getInt("id");
        appearance = jsonDefMess.getString("appearance");
        begin_date = jsonDefMess.getString("begin_date");
        end_date = jsonDefMess.getString("end_date");
        location = jsonDefMess.getString("location");
        description = jsonDefMess.getString("description");
        is_published = jsonDefMess.getBoolean("is_published");
        this.user = user;
    }

}
