package com.example.defmess.ui.user;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public String name;
    public String surname;
    public Integer age;
    public String address;
    public String email;
    public String link_to_photo;
    public String description;

    public User(JSONObject jsonUser) throws JSONException {
        name = jsonUser.getString("name");
        surname = jsonUser.getString("surname");
        age = jsonUser.getInt( "age");
        address = jsonUser.getString("address");
        email = jsonUser.getString("email");
        link_to_photo = jsonUser.getString("link_to_photo");
        description = jsonUser.getString("description");
    }
}
