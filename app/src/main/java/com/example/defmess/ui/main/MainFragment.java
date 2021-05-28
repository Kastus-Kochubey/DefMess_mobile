package com.example.defmess.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentMainBinding;
import com.example.defmess.ui.defmess.DefMess;
import com.example.defmess.ui.defmess.DefMessAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment {
//    private MainViewModel mainViewModel;
    private FragmentMainBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;


//            RequestToServer request = new RequestToServer("https://82.148.29.139");
        RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("email", "russian_post");
//            jsonObject.put("password", "1234");
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
            JSONObject jsonResponse = request.get("/defmess/publicated");
            if (jsonResponse != null) {
                JSONArray pubDefMessages = jsonResponse.getJSONArray("pub_def_messages");
                DefMessAdapter defMessAdapter = new DefMessAdapter(pubDefMessages);
                recyclerView.setAdapter(defMessAdapter);

//                mainViewModel.getPubDefMessages().observe(getViewLifecycleOwner(), new Observer<JSONArray>() {
//                    @Override
//                    public void onChanged(JSONArray jsonArray) {
//
//                    }
//                });
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
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
