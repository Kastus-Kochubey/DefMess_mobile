package com.example.defmess.ui.user.profile;

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
import com.example.defmess.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

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
        Button button = binding.buttonBack;

        button.setOnClickListener((v) -> {
            Navigation.findNavController(container).navigate(R.id.nav_main);
        });
        return root;
    }
}
