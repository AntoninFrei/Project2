

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
            System.out.println("cccccc");
            System.out.println(json);

            JSONObject obj = new JSONObject(json);

            JSONObject readedData = obj.getJSONObject("rates");
            Iterator<String> keys = readedData.keys();
            //JSONArray readedJ = readedData.getJSONArray("rates");
            while(keys.hasNext()) {
                String key = keys.next();
                System.out.println(key);
            }
            System.out.println("xxxxxxxxxxxxxxxxxxxx");
            System.out.println(readedData);
//            JSONObject obj2 = new JSONObject(readedData);
//            JSONObject data2 = obj2.getJSONObject("CZ");
//            System.out.println(data2);
//            System.out.println("xxxxxxooooooooooooooooooooooooooooooxxxxxxxxxxxxx");
            final int n = readedData.length();
//            for (int i = 0; i < n; ++i) {
//                System.out.println("xxx");
//                System.out.println(readedData.get("AT"));
                //System.out.println(readedData.getJSONObject(String.valueOf(i)));
                //JSONObject person = readedData.getJSONObject(i);
                //System.out.println(person.getInt("county"));
//
//            }
//            for(JSONObject read: readedJ) {
//                System.out.println("xxx");
//            }
            System.out.println("xxxxxxxxxxxxxxxxxxxxx");
            System.out.println(readedData.getJSONObject("AT").getString("country"));

        }


        //System.out.println("myNumber:\t" + readMO.getMyNumber());

//            ObjectMapper objectMapper = new ObjectMapper();
//
//
//
//
////
////
////        // načtení ze souboru
//    	try {
//            MyObject readMO = objectMapper.readValue(json, MyObject.class);
//            System.out.println("MyObject načten.");
////            // rozbrazení načtených hodnot
////            //System.out.println("myNumber:\t" + readMO.getMyNumber());
////            //System.out.println("myBoolean:\t" + readMO.isMyBoolean());
////            //System.out.println("myString:\t" + readMO.getMyString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    }



