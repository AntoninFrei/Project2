

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;

import com.engeto.project2.MyObject;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.annotation.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.net.URI;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





    public class Main {
        public static void main(String[] args) {
            String json = ClientBuilder.newClient().target("https://euvatrates.com/rates.json").request().accept(MediaType.APPLICATION_JSON).get(String.class);
            //System.out.println("cccccc");
            System.out.println(json);

            JSONObject obj = new JSONObject(json);

            JSONObject readedData = obj.getJSONObject("rates");
            Iterator<String> keys = readedData.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                System.out.println(key);
                System.out.println(readedData.getJSONObject(key).getString("country"));
                String country = readedData.getJSONObject(key).getString("country");
                System.out.println(readedData.getJSONObject(key).getBigDecimal("standard_rate"));
                //System.out.println(readedData.getJSONObject(key).getBigDecimal("reduced_rate"));

                try {
                    System.out.println(readedData.getJSONObject(key).getBigDecimal("reduced_rate"));
                }
                catch (org.json.JSONException e) {
                    System.out.println(readedData.getJSONObject(key).getBoolean("reduced_rate"));
                    System.out.println("Je to Boolean");
                }

                try {
                    System.out.println("reduced_rate_alt" + readedData.getJSONObject(key).getBigDecimal("reduced_rate_alt"));
                }
                catch (org.json.JSONException e) {
                    System.out.println("reduced_rate_alt" + readedData.getJSONObject(key).getBoolean("reduced_rate_alt"));
                    System.out.println("Je to Boolean");
                }

                try {
                    System.out.println(readedData.getJSONObject(key).getBigDecimal("super_reduced_rate"));
                }
                catch (org.json.JSONException e) {
                    System.out.println(readedData.getJSONObject(key).getBoolean("super_reduced_rate"));
                    System.out.println("Je to Boolean");
                }


                try {
                    System.out.println(readedData.getJSONObject(key).getBigDecimal("parking_rate"));
                }
                catch (org.json.JSONException e) {
                    System.out.println(readedData.getJSONObject(key).getBoolean("parking_rate"));
                    System.out.println("Je to Boolean");
                }

                System.out.println("Pomoci funkce " + getIsHas(readedData, "super_reduced_rate"));
            }






        }



        static Boolean getIsHas(JSONObject readedData, String key) {
            Boolean out;
            try {
                out = readedData.getJSONObject(key).getBoolean(key);
            }
            catch (org.json.JSONException e) {
                out = true;

            }
            return out;
        }

    }




