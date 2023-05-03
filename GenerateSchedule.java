package org.exploremore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.*;
import java.net.URL;


public class GenerateSchedule {
    Location place = new Location();
    int i = 0;
    public Location GenerateSchedule(String[] location, String[] filters, Boolean[] filterStatus) {
        try {
            String loc = "";
            for (String word : location) {
                loc += "%20" + word;
            }
            if ( filters.length == 0) {
                // error, must select filters first
            }
            else {

                int num = 0;
                for(String filter : filters) {
                        if(filterStatus[num] == true) {
                            String urlStr = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + filter + "%20in" + loc + "&radius=2000&key=AIzaSyAp98gEOtFN1KOQJZsyoQsDQfAxc7Ivb5M";
                            OkHttpClient client = new OkHttpClient().newBuilder()
                                    .build();
                            MediaType mediaType = MediaType.parse("text/plain");
                            RequestBody body = RequestBody.create(mediaType, "");
                            Request request = new Request.Builder()
                                    .url(urlStr)
                                    .method("GET", null)
                                    .build();
                            Response response = client.newCall(request).execute();
//                    System.out.print(response);

                            int responseCode = response.code();
                            if ((responseCode) == 200) {

                                URL url = new URL(urlStr);

                                JsonNode json = new ObjectMapper().readTree(url);
                                JsonNode res = json.get("results");

                                for (JsonNode y : res) {
                                    double rating = Double.valueOf(y.get("rating").asText());
                                    if (rating >= 4.0) {
                                        place.setType(filter, i);
                                        place.setAddress(String.valueOf(y.get("formatted_address")), i);
                                        place.setName(String.valueOf(y.get("name")), i);
                                        place.setRating(Double.valueOf(y.get("rating").asText()), i);
//                                System.out.println((y.get("name"))+" "+y.get("formatted_address"));
                                        i++;
                                    }
                                }
                            }
                        }
                    num++;
                }

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        place.setI(i);
        return place;

    }
}
