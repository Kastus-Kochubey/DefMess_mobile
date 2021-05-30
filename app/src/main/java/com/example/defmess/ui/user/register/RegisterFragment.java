package com.example.defmess.ui.user.register;

import android.os.Bundle;
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

import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentRegisterBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        EditText name = binding.registerName;
        EditText surname = binding.registerSurname;
        EditText email = binding.registerEmail;
        EditText password = binding.registerPassword;
        EditText passwordAgain = binding.registerPasswordAgain;
        Button register_butt = binding.registerButt;

        register_butt.setOnClickListener((v) -> {
            if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
                Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                password.setText("");
                passwordAgain.setText("");
            }

            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
            try {
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("name", name.getText());
                jsonRequest.put("surname", surname.getText());
                jsonRequest.put("email", email.getText());
                jsonRequest.put("password", password.getText());
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
                JSONObject jsonResponse = request.post("/user/register", jsonRequest.toString());
                if (jsonResponse != null) {
                    Toast.makeText(getContext(), jsonResponse.toString(), Toast.LENGTH_SHORT).show();
                }
            } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }
            // TODO: check server response / check user's data unique on server
            Navigation.findNavController(root).navigate(R.id.nav_login);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
