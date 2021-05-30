package com.example.defmess.ui.user.profile;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defmess.JsonFilesManager;
import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentProfileBinding;
import com.example.defmess.databinding.FragmentUserBinding;
import com.example.defmess.ui.defmess.DefMessAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
//    public ProfileFragment() {
//        super(R.layout.fragment_profile);
//    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        Button button = container.findViewById(R.id.button_back);


        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.recyclerView;
        FragmentUserBinding userFragment = binding.userFragment;

//        RequestToServer request = new RequestToServer("https://82.148.29.139");
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        try {
            JsonFilesManager filesManager = new JsonFilesManager(getContext().getApplicationContext());
            JSONObject jsonUserProfile = request.post("/user/profile",
                    new JSONObject()
                            .put("jwt_code",
                                    filesManager.get("jwt_code")
                                            .toString()).toString()).getJSONObject("user");

            filesManager.add("user", jsonUserProfile).save();
            DefMessAdapter adapter = new DefMessAdapter(jsonUserProfile);
            recyclerView.setAdapter(adapter);
//


            userFragment.userNameHeader.setText(jsonUserProfile.get("name").toString());
            userFragment.userSurnameHeader.setText(jsonUserProfile.get("surname").toString());

            RequestToServer requestImage = new RequestToServer("http://127.0.0.1:5000");
            Bitmap bitmapResponse = requestImage.getImage(jsonUserProfile.get("link_to_photo").toString());
            if (bitmapResponse != null) {
                userFragment.userAvatarHeader.setImageBitmap(bitmapResponse);
            }

        } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
//

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}




