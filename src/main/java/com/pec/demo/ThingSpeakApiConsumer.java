package com.pec.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ThingSpeakApiConsumer {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String apiKey = "H5BHYNPYD78QROKP";
        String channelId = "1956370";
        int fieldNumber = 1;
        int results = 10;

        String url = String.format("https://api.thingspeak.com/channels/%s/fields/%s.json?api_key=%s&results=%s",
                channelId, fieldNumber, apiKey, results);
        "String url = String.format(\"https://api.thingspeak.com/channels/%s/fields/%s.json?api_key=%s&results=%s\",channelId, \"1\", apiKey, results);\r\n"
        + ""
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String fieldValue = jsonObject.getString("field" + fieldNumber);
                String createdDate = jsonObject.getString("created_at");
                System.out.println("Field " + fieldNumber + " value: " + fieldValue + ", created at: " + createdDate);
            }
        } else {
            System.out.println("Failed to get data from ThingSpeak API");
        }
    }
}
