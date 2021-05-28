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
import com.example.defmess.databinding.FragmentMainBinding;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    private FragmentMainBinding binding;
    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        Button button = container.findViewById(R.id.button_back);


        binding = FragmentMainBinding.inflate(inflater, container, false);
        Button button = binding.button;

        button.setOnClickListener((v) -> {
            Navigation.findNavController(container).navigate(R.id.action_nav_profile_to_nav_main);
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
