import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
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


    public String post(String path, String json) throws IOException, ExecutionException, InterruptedException {
        RequestBody body = RequestBody.create(json, JSON);
        Log.e("post", "json: " + body.toString() + " url: " + address + path);
        Request request = new Request.Builder()
                .url(address + path)
                .post(body)
                .build();
        MakeReq req = new MakeReq();
        String json_response = req.execute(request).get();
        Log.e("post", "response: " + json_response);
        return json_response;

    }


    protected class MakeReq extends AsyncTask<Request, Void, String> {
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
}
