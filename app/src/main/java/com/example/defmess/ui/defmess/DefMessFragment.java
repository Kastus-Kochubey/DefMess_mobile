package com.example.defmess.ui.defmess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.defmess.R;
import com.example.defmess.databinding.FragmentDefmessBinding;
import com.example.defmess.ui.main.MainViewModel;

import org.jetbrains.annotations.NotNull;

public class DefMessFragment extends Fragment {
    FragmentDefmessBinding binding;
    MainViewModel mainViewModel;
    public Integer currDefMessId;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentDefmessBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mainViewModel = new ViewModelProvider(requireActivity()).get(mainViewModel.getClass());
        binding.setViewModel(mainViewModel);

        return root;
    }

    public void onItemClick(Integer defmess_id){
        currDefMessId = defmess_id;
    }
}
