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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defmess.JsonFilesManager;
import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentProfileBinding;
import com.example.defmess.databinding.FragmentUserBinding;
import com.example.defmess.ui.defmess.DefMessAdapter;
import com.example.defmess.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    MainViewModel mainViewModel;
//    public ProfileFragment() {
//        super(R.layout.fragment_profile);
//    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        View root = binding.getRoot();
//        binding.setViewModel(mainViewModel);

        if (!mainViewModel.isLogin()) {
            Navigation.findNavController(root).navigate(R.id.nav_reg_log);
        }

        RecyclerView recyclerView = binding.recyclerView;
        FloatingActionButton newDefMessButt = binding.floatingActionButton;

        newDefMessButt.setOnClickListener((v) -> {
            Navigation.findNavController(root).navigate(R.id.nav_createDefmess);
        });


        try {
            DefMessAdapter adapter = new DefMessAdapter(mainViewModel.getProfile().getValue(),
                    mainViewModel.getUser().getValue());
            recyclerView.setAdapter(adapter);
        } catch (JSONException | InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

//            userFragment.userNameHeader.setText(jsonUserProfile.get("name").toString());
//            userFragment.userSurnameHeader.setText(jsonUserProfile.get("surname").toString());
//
//            RequestToServer requestImage = new RequestToServer("http://127.0.0.1:5000");
//            Bitmap bitmapResponse = requestImage.getImage(jsonUserProfile.get("link_to_photo").toString());
//            if (bitmapResponse != null) {
//                userFragment.userAvatarHeader.setImageBitmap(bitmapResponse);
//            }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}




