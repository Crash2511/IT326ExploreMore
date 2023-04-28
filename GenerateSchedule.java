package org.exploremore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GenerateSchedule {
    GenerateSchedule(String txt) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://maps.googleapis.com/maps/api/place/textsearch/json?input=church&inputtype=textquery&locationbias=circle%3A2000%4047.6918452%2C-122.2226413&key=AIzaSyAp98gEOtFN1KOQJZsyoQsDQfAxc7Ivb5M")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.print(response);

            int responseCode = 0;
            if ((responseCode = response.code()) == 200) {

                URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?input=church&inputtype=textquery&locationbias=circle%3A2000%4047.6918452%2C-122.2226413&key=AIzaSyAp98gEOtFN1KOQJZsyoQsDQfAxc7Ivb5M");
                String jsonStr = IOUtils.toString(url, UTF_8);

                JsonNode json = new ObjectMapper().readTree(url);
                JsonNode res = json.get("results");
                // getting address

                for(JsonNode x : res) {
                    double rating = Double.valueOf(x.get("rating").asText());
                    if (rating >= 4.0) {

                        System.out.println(x.get("name"));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
