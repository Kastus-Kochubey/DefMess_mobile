package com.example.defmess;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestToServer {
    String address;
    OkHttpClient client = new OkHttpClient();


    public RequestToServer(String address) {
        this.address = address;
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public JSONObject post(String path, String json) throws IOException, ExecutionException, InterruptedException, JSONException {
        RequestBody body = RequestBody.create(json, JSON);
        Log.e("post", "json: " + body.toString() + " url: " + address + path);
        Request request = new Request.Builder()
                .url(address + path)
                .post(body)
                .build();
        MakeReqString req = new MakeReqString();
        String json_response = req.execute(request).get();
//        Log.e("post", "response: " + json_response + json);
        return new JSONObject(json_response);

    }

    public JSONObject get(String path) throws ExecutionException, InterruptedException, JSONException {
        Request request = new Request.Builder().url(address + path).get().build();

        MakeReqString makeReqString = new MakeReqString();
        JSONObject jsonResp = new JSONObject(makeReqString.execute(request).get());
        Log.e("get", "response: " + jsonResp);
        return jsonResp;
    }

    public Bitmap getImage(String path) throws ExecutionException, InterruptedException {
        Request request = new Request.Builder()
                .url(address + path)
                .get()
                .build();
        MakeReqBitmap req = new MakeReqBitmap();
        return req.execute(request).get();

    }


    protected class MakeReqString extends AsyncTask<Request, Void, String> {
        @Override
        protected String doInBackground(Request... requests) {
            try (Response response = client.newCall(requests[0]).execute()) {
//                String json_res = response.body().string();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    protected class MakeReqBitmap extends AsyncTask<Request, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Request... requests) {
            try (Response response = client.newCall(requests[0]).execute()) {
//                String json_res = response.body().string();
                InputStream inputStream = response.body().byteStream();
                return BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
