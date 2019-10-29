package com.team.getandpost;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RequestAsync().execute();
    }
    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                //return RequestHandler.sendGet("https://api.jsonbin.io/b/5db7ea7017673f71e9157bf8");

                // POST Request
                /*JSONObject postDataParams = new JSONObject();
                postDataParams.put("name", "Manjeet");
                postDataParams.put("email", "manjeet@gmail.com");
                postDataParams.put("phone", "+1111111111");*/

                //PUT Request
                return jsonPUT();
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null){
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }

        private String jsonPUT() throws IOException, JSONException {
            JSONArray array = new JSONArray();
            for(int i = 0; i < 10; i ++) {
                JSONObject postData = new JSONObject();
                postData.put("name", "morpheus");
                postData.put("job", "leader");
                array.put(postData);
            }

            URL url = new URL("https://api.jsonbin.io/b/5db729fbc24f785e64f6226c");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(10000);

            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    out, "UTF-8"));
            writer.write(array.toString());
            writer.flush();

            //return String.valueOf(connection.getResponseCode());
            return RequestHandler.sendGet("https://api.jsonbin.io/b/5db729fbc24f785e64f6226c/latest");
        }


    }
}
