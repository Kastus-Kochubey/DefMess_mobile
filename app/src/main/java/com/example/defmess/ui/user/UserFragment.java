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
import androidx.lifecycle.ViewModelProvider;

import com.example.defmess.JsonFilesManager;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentUserBinding;
import com.example.defmess.ui.main.MainViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class UserFragment extends Fragment {
    public FragmentUserBinding binding;
    public MainViewModel mainViewModel;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);
        TextView name = binding.userNameHeader;
        TextView surname = binding.userSurnameHeader;
        ImageView avatar = binding.userAvatarHeader;


        User user = mainViewModel.getUser().getValue();
        name.setText(user.name);
        surname.setText(user.surname);
        avatar.setImageBitmap(mainViewModel.getUserAvatar());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
