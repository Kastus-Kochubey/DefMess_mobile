package com.example.defmess;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ExampleFragment extends Fragment {
    public ExampleFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Button button = view.findViewById(R.id.button);
////        Button button = view.
////        ScrollView scrollView = view.findViewById(R.id.ScrollView);
//        ScrollView scrollView = view.findViewById(R.id.ScrollView);
//
//        LinearLayout linearLayout = view.findViewById(R.id.LinearLayout);
//        ImageButton imageButton = view.findViewById(R.id.imageButton);


//        button.setOnClickListener((v) -> {
////            RequestToServer request = new RequestToServer("https://82.148.29.139");
//            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
//            try {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("email", "russian_post");
//                jsonObject.put("password", "1234");
////                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
//                String text = request.post("/user/login", jsonObject.toString());
//                for (int i = 0; i < 20; i++) {
//                    TextView textView = new TextView(view.getContext());
//                    textView.setTextColor(Color.rgb(255, 13 * i % 256, 150));
//                    textView.setText(text);
//                    linearLayout.addView(textView);
//                }
//            } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
//                e.printStackTrace();
//            }
//
//        });
//        imageButton.setOnClickListener((v) -> {
//
//        });
    }
}

//class Request extends AsyncTask<String, Void, String> {
//    @Override
//    protected String doInBackground(String... strings) {
////        String urlText = strings[0];
////        if (!urlText.equals("")) {
//        try {
//            URL url = new URL("https://82.148.29.139/api/users");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            String text = "";
//            InputStream inputStream = connection.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader rd = new BufferedReader(inputStreamReader);
//            String line;
//            while ((line = rd.readLine()) != null) {
//                text += line;
//            }
//            rd.close();
//            connection.disconnect();
//            return text;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        }
//        return null;
//    }
//}