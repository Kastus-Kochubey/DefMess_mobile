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
import androidx.fragment.app.Fragment;

import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment {
    //    private MainViewModel mainViewModel;

//    public MainFragment (){
//        super(R.layout.fragment_main);
//    }


    private FragmentMainBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        mainViewModel =
//                new ViewModelProvider(this).get(MainViewModel.class);


        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button button = binding.button;
        ScrollView scrollView = binding.ScrollView;
        LinearLayout linearLayout = binding.LinearLayout;

        button.setOnClickListener((v) -> {
//            RequestToServer request = new RequestToServer("https://82.148.29.139");
            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", "russian_post");
                jsonObject.put("password", "1234");
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
                JSONObject jsonResponse = request.post("/user/login", jsonObject.toString());
                if (jsonResponse != null) {
                    for (int i = 0; i < 20; i++) {
                        TextView textView = new TextView(root.getContext());
                        textView.setTextColor(Color.rgb(255, 3 * i % 200, 7 * i % 256));
                        textView.setText(jsonResponse.toString());
                        linearLayout.addView(textView);
                    }
                }
            } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }

        });

        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
