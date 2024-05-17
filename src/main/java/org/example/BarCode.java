package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BarCode {
    public static void main(String[] args) throws IOException {
        getDiscountedPrice();
    }

    //    Given a barcode, query the API at
//    https://jsonmock.hackerrank.com/api/inventory?barcode=barcode
//    and return the item's discounted price the response is a json objects with 5 fields.
//    the essential field is data: data: either an empty array of an array with a single object that contains the item's record and in the data array,
//    the item has the following schema: barcode - the barcode the product (String) price- the gross selling price,
//    discount: the discount percent to apply, some field that are not of interest.
//    if the barcode is found return the data array contains exactly 1 element if not the function should return "-1".
//    use the discount and the price properties to calculate the discounted price rounded to the nearest integer. solve in Java

    public static int getDiscountedPrice()  {
        String url = "https://jsonmock.hackerrank.com/api/inventory?barcode%20";
        try{
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
                    StringBuilder res = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        res.append(line);
                    }
                    System.out.println("respond: " + res);

                    JSONObject jsonObject = new JSONObject(res.toString());
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    System.out.println("DataArray: " + dataArray);

                    if(!dataArray.isEmpty()){
                        JSONObject item = dataArray.getJSONObject(0);
                        double price = item.getDouble("price");
                        double discount = item.getDouble("discount");
                        double discountedPrice = price * (1 - discount / 100);
                        System.out.println("discountedPrice: " + discountedPrice);
                        return (int) Math.round(discountedPrice);

                    }else {
                        return -1; // Barcode not found
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else {
                return -1;
            }

        } catch(IOException exc){
            throw new RuntimeException(exc);
        }
        return -1;
    }
}
