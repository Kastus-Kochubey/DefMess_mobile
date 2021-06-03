package com.example.defmess.ui.defmess;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defmess.DateUtils;
import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.ui.main.MainViewModel;
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
    MainViewModel mainViewModel;

    public DefMessAdapter(List<DefMess> list) {
        this.list = list;
    }

    public DefMessAdapter(JSONArray defMessages) throws JSONException {
        for (int i = 0; i < defMessages.length(); i++) {
            JSONObject jsonDefMess = defMessages.getJSONObject(i).getJSONObject("defmess");
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

    public DefMessAdapter(JSONArray defMessages, User user) throws JSONException {
//        Log.e("jsonUserProfile", jsonUserProfile.toString());
//        JSONArray jsonArray = jsonUserProfile.getJSONArray("def_messages");

        for (int i = 0; i < defMessages.length(); i++) {
            JSONObject jsonDefMess = defMessages.getJSONObject(i).getJSONObject("defmess");
            Log.e("JsonDefMess", jsonDefMess.toString());
            DefMess defMess = new DefMess(jsonDefMess, user);
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
        holder.defmessEndDate.setText(defMess.end_date);
//        holder.itemView.setOnClickListener((v) -> {
//            new DefMessFragment().onItemClick(position);
//
//        });

        if (defMess.is_published) {
            holder.itemView.setBackgroundColor(Color.rgb(255, 116, 116));
        } else if (defMess.is_published == null) {
            holder.itemView.setBackgroundColor(Color.rgb(125, 255, 69));
        } else if (!(defMess.is_published)) {
            holder.itemView.setBackgroundColor(Color.rgb(93, 139, 255));
        }

        {
//            MainViewModel mainViewModel = new MainViewModel();
//            Bitmap bitmap = mainViewModel.getUserAvatar();
//            if (bitmap != null) {
//                holder.userAvatar.setImageBitmap(bitmap);
//            }
        }
//        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
//        try {
//
//            Bitmap bitmapResponce = request.getImage(defMess.user.link_to_photo);
//            if (bitmapResponce != null) {
//                holder.userAvatar.setImageBitmap(bitmapResponce);
//            }
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }

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
        TextView defmessEndDate;


        public DefMessHolder(@NonNull @NotNull View view) {
            super(view);
            userName = view.findViewById(R.id.userName);
            userSurname = view.findViewById(R.id.userSurname);
            userEmail = view.findViewById(R.id.userEmail);
            userAvatar = view.findViewById(R.id.userAvatar);
            defmessLocation = view.findViewById(R.id.defmessLocation);
            defmessEndDate = view.findViewById(R.id.defmessEndDate);
        }
    }
}
