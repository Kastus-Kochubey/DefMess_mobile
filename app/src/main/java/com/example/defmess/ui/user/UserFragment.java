package com.example.defmess.ui.user;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.defmess.JsonFilesManager;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentUserBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class UserFragment extends Fragment {
    public FragmentUserBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView name = binding.userNameHeader;
        TextView surname = binding.userSurnameHeader;
        ImageView avatar = binding.userAvatarHeader;

        Log.e("context", "UserFragment");

        try {
            JsonFilesManager manager = new JsonFilesManager(getContext().getApplicationContext());
            User user = new User(manager.getJSONObject("user"));

            Log.e("context", container.getContext().toString());

            name.setText(user.name);
            surname.setText(user.surname);

//            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
//            Bitmap bitmapResponse = request.getImage(user.link_to_photo);
//            if (bitmapResponse != null) {
//                avatar.setImageBitmap(bitmapResponse);
//            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
