package com.example.defmess.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.defmess.R;
import com.example.defmess.databinding.FragmentLogRegBinding;
import com.example.defmess.databinding.FragmentUserBinding;

import org.jetbrains.annotations.NotNull;

public class RegLogFragment extends Fragment {
    FragmentLogRegBinding binding;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentLogRegBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button toLogin = binding.toLogin;
        Button toRegister = binding.toRegister;

        toLogin.setOnClickListener((v) -> {
            Navigation.findNavController(root).navigate(R.id.nav_login);
        });

        toRegister.setOnClickListener((v) -> {
            Navigation.findNavController(root).navigate(R.id.nav_reg);
        });


        return root;
    }
}
