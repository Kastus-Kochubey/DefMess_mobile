package com.example.defmess.ui.defmess.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.defmess.databinding.FragmentCreateDefmessBinding;

import org.jetbrains.annotations.NotNull;

public class CreateDefMessFragment extends Fragment {
    private FragmentCreateDefmessBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentCreateDefmessBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
