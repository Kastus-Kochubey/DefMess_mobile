package com.example.defmess.ui.user.login;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.defmess.JsonFilesManager;
import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText email = binding.loginEmail;
        EditText password = binding.loginPassword;
        Button login_butt = binding.loginButt;

        login_butt.setOnClickListener((v) -> {
            //            RequestToServer request = new RequestToServer("https://82.148.29.139");
            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", email.getText());
                jsonObject.put("password", password.getText());
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
                JSONObject jsonResponse = request.post("/user/login", jsonObject.toString());
                if (jsonResponse != null) {
                    Toast.makeText(getContext(), jsonResponse.toString(), Toast.LENGTH_SHORT).show();
                    JsonFilesManager jsonFilesManager = new JsonFilesManager(getContext().getApplicationContext());
                    jsonFilesManager.add("jwt_code", jsonResponse.getString("jwt_code"));
                    jsonFilesManager.save();

//                    Toast.makeText(getContext(), "qqqqqqqqq" + jsonFilesManager.getJson().toString(), Toast.LENGTH_SHORT).show();
//                    Snackbar.make(binding.getRoot(), Calendar.getInstance().toString(), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                }
            } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }
            // TODO: check server response / check user's data unique on server
            Navigation.findNavController(root).navigate(R.id.nav_profile);
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
