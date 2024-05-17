package org.example;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AgeCount {

    public static void main(String[] args) {
        ageCalculation();
    }

    private static int ageCalculation() {
        String url = "https://coderbyte.com/api/challenges/json/age-counting";
        try{
            URL url2 = new URL(url);
            HttpURLConnection con = (HttpURLConnection) url2.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                    StringBuilder str1 = new StringBuilder();
                    String line2;
                    while((line2 = reader.readLine()) != null) {
                        str1.append(line2);
                    }
                    JSONObject myObject = new JSONObject(str1.toString());
                    String keys = (String) myObject.get("data");

                    String[] keySep = keys.split(", ");
                    ArrayList<String> outArray = new ArrayList<>();

                    Map<String, Integer> keyData = new HashMap<>();
                    for(int i = 0; i<keySep.length; i+=2){
                        String key = keySep[i].split("=")[1];
                        int val = Integer.parseInt(keySep[i+1].split("=")[1]);
                        if(val >= 50){
                            outArray.add("key=" + key + ", age=" + val);
                        }
                    }

                    System.out.println(String.join(", ", outArray ));

                } catch (IOException e){
                    e.printStackTrace();
                }
            } else
                return -1;
        }catch (IOException exce){
            throw new RuntimeException(exce);
        }


        return -1;
    }
}
