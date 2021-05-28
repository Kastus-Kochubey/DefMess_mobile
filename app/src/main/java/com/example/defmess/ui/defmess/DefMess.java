package com.example.defmess.ui.defmess;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DefMess {
    Integer id;
    String appearance;
    String begin_date;
    String end_date;
    String location;
    String description;
    Boolean is_published;

    public DefMess(JSONObject jsonObject) throws JSONException {
//        for (String name : new String[]{"id", "appearance", "begin_date",
//                "end_date", "location", "description", "is_published"}) {
//            jsonObject.get(name);
//        }
        JSONObject jsonDefMess = jsonObject.getJSONObject("defmess");
        Log.e("JSONObject to DefMess", jsonObject.toString());
        id = jsonDefMess.getInt("id");
        appearance =  jsonDefMess.getString("appearance");
        begin_date =  jsonDefMess.getString("begin_date");
        end_date =  jsonDefMess.getString("end_date");
        location =  jsonDefMess.getString("location");
        description =  jsonDefMess.getString("description");
        is_published =  jsonDefMess.getBoolean("is_published");
    }

}
