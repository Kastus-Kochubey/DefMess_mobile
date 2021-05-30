package com.example.defmess;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class JsonFilesManager {
    private static final String FILENAME = "data.json";
    private final Context context;
    private JSONObject jsonFile;

    public JsonFilesManager(Context appContext) throws IOException, JSONException {
        this.context = appContext;
        jsonFile = getJson();
    }

    public JsonFilesManager(Context appContext, Boolean load) throws IOException, JSONException {
        this.context = appContext;
        if (load) jsonFile = getJson();
    }


    public void save() throws IOException {
        FileOutputStream outputStream = context.openFileOutput(FILENAME, MODE_PRIVATE);
        outputStream.write(jsonFile.toString().getBytes());
        outputStream.close();

    }


    private JSONObject getJson() throws IOException, JSONException {
        FileInputStream inputStream = context.openFileInput(FILENAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder str = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            str.append(line);
        }
        return new JSONObject(str.toString());

    }

    public JsonFilesManager add(String name, Object value) throws IOException, JSONException {
        jsonFile.put(name, value);
        return this;
    }

    public Object get(String name) throws JSONException {
        if (jsonFile != null){
            return jsonFile.get(name);
        }
        return null;
    }

    public JSONObject getJSONObject(String name) throws JSONException {
        if (jsonFile != null){
            return jsonFile.getJSONObject(name);
        }
        return null;
    }

//    public void add(String name, JSONObject jsonObject) throws IOException, JSONException {
//        save(load().put(name, jsonObject));
//    }
}
