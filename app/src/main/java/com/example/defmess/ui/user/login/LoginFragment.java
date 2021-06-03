package com.example.defmess.ui.user.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.defmess.R;
import com.example.defmess.databinding.FragmentLoginBinding;
import com.example.defmess.ui.main.MainViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    MainViewModel mainViewModel;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);


        EditText email = binding.loginEmail;
        EditText password = binding.loginPassword;
        Button login_butt = binding.loginButt;

        login_butt.setOnClickListener((v) -> {
            //            RequestToServer request = new RequestToServer("https://82.148.29.139");
//            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
//            try {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("email", email.getText());
//                jsonObject.put("password", password.getText());
////                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
//                JSONObject jsonResponse = request.post("/user/login", jsonObject.toString());
//                if (jsonResponse != null) {
//                    Toast.makeText(getContext(), jsonResponse.toString(), Toast.LENGTH_SHORT).show();
//
//                    JsonFilesManager jsonFilesManager = new JsonFilesManager(getContext().getApplicationContext());
//                    String string = jsonResponse.getString("jwt_code");
//                    Log.e("string", string);
//                    jsonFilesManager.add("jwt_code", string).save();
////                    jsonFilesManager.save();
//
////                    Toast.makeText(getContext(), "qqqqqqqqq" + jsonFilesManager.getJson().toString(), Toast.LENGTH_SHORT).show();
////                    Snackbar.make(binding.getRoot(), Calendar.getInstance().toString(), Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//                }
//            } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
//                e.printStackTrace();
//            }
            // TODO: check server response / check user's data unique on server

            try {
                if (mainViewModel.login(email.getText().toString(), password.getText().toString())){
                    Navigation.findNavController(root).navigate(R.id.nav_profile);
                }
            } catch (JSONException | InterruptedException | ExecutionException | IOException e) {
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
