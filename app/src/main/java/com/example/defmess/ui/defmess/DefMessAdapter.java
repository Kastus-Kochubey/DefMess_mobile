package com.example.defmess.ui.defmess;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.ui.user.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DefMessAdapter extends RecyclerView.Adapter<DefMessAdapter.DefMessHolder> {

    List<DefMess> list = new ArrayList<>();

    public DefMessAdapter(List<DefMess> list) {
        this.list = list;
    }
    public DefMessAdapter(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonDefMess = jsonArray.getJSONObject(i).getJSONObject("defmess");
            Log.e("JsonDefMess", jsonDefMess.toString());
            DefMess defMess = new DefMess(jsonDefMess);
            list.add(defMess);
        }
    }

    public DefMessAdapter(JSONObject jsonUserProfile) throws JSONException {
        Log.e("jsonUserProfile", jsonUserProfile.toString());
        JSONArray jsonArray = jsonUserProfile.getJSONArray("def_messages");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonDefMess = jsonArray.getJSONObject(i).getJSONObject("defmess");
            Log.e("JsonDefMess", jsonDefMess.toString());
            DefMess defMess = new DefMess(jsonDefMess, new User(jsonUserProfile));
            list.add(defMess);
        }
    }

    @NonNull
    @NotNull
    @Override
    public DefMessHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_defmess, parent, false);
        return new DefMessHolder(view);
    }

//    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull DefMessHolder holder, int position) {
        DefMess defMess = list.get(position);
        holder.userName.setText(defMess.user.name);
        holder.userSurname.setText(defMess.user.surname);
        holder.userEmail.setText(defMess.user.email);

        holder.defmessLocation.setText(defMess.location);


        if (defMess.is_published) {
            holder.itemView.setBackgroundColor(Color.rgb(255, 116, 116));
        }else if (defMess.is_published == null) {
            holder.itemView.setBackgroundColor(Color.rgb(125, 255, 69));
        } else if (!(defMess.is_published)) {
            holder.itemView.setBackgroundColor(Color.rgb(93, 139, 255));
        }

        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        try {

            Bitmap bitmapResponce = request.getImage(defMess.user.link_to_photo);
            if (bitmapResponce != null) {
                holder.userAvatar.setImageBitmap(bitmapResponce);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DefMessHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView userSurname;
        TextView userEmail;
        ImageView userAvatar;

        TextView defmessLocation;

        public DefMessHolder(@NonNull @NotNull View view) {
            super(view);
            userName = view.findViewById(R.id.userName);
            userSurname = view.findViewById(R.id.userSurname);
            userEmail = view.findViewById(R.id.userEmail);
            userAvatar = view.findViewById(R.id.userAvatar);
            defmessLocation = view.findViewById(R.id.defmessLocation);
        }
    }
}
