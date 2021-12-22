

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.engeto.project2.State;
import com.engeto.project2.StateException;
import com.engeto.project2.StateList;
import com.engeto.project2.TaxRate;


//import com.fasterxml.jackson.annotation.JsonMappingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.json.JSONObject;

import static com.engeto.project2.ReadInput.safeInput;


public class Main {
        public static void main(String[] args) throws StateException {
            String json = ClientBuilder.newClient().target("https://euvatrates.com/rates.json").request().accept(MediaType.APPLICATION_JSON).get(String.class);

            System.out.println(json);

            JSONObject obj = new JSONObject(json);

            JSONObject readedData = obj.getJSONObject("rates");
            Iterator<String> keys = readedData.keys();



            StateList listOfState = new StateList();

            while(keys.hasNext()) {
                String countryShort = keys.next();

                String country = readedData.getJSONObject(countryShort).getString("country");


                TaxRate standardRate = new TaxRate(readedData, countryShort, "standard_rate",
                        "Základní sazba");
                TaxRate reducedRate = new TaxRate(readedData, countryShort, "reduced_rate",
                        "1. snížená sazba");
                TaxRate reducedRateAlt = new TaxRate(readedData, countryShort, "reduced_rate_alt",
                        "2. snížená sazba");
                TaxRate superReducedRate = new TaxRate(readedData, countryShort, "super_reduced_rate",
                        "Super snížená sazba");
                TaxRate parkingRate = new TaxRate(readedData, countryShort, "parking_rate",
                        "Speciální sazba");


                State state = new State(country,countryShort, standardRate, reducedRate, reducedRateAlt,
                        superReducedRate, parkingRate);


                State findState = listOfState.getItemWithName(state);
                if (findState != null) {
                    findState.addShorCut(state.getFirstShortCut());
                    //listOfState.setAltShortCut(state);
                    System.out.println("Duplicita " + state.getName());
                } else {
                    listOfState.add(state);
                }



            }
            boolean nextInput = true;
            while(nextInput) {
                System.out.println("Vyber volbu:\n\t1 - Vytisky 3 země s nejnižší sazbou\n\t2 - Vytiskni 3 zeme " +
                        "z nejvyssi zakladní sazbou\n\t3 - soubor\n\t4 - zadej\n\t5 - konec");
                String inputString = safeInput();
                if (inputString.equals("1")) {
                    System.out.println(listOfState.getMin());
                }
                else if (inputString.equals("2")) {
                    System.out.println(listOfState.getMax());
                }
                else if (inputString.equals("3")) {
                    try {
                        System.out.println(exportToFile(listOfState.getMin() + "\n\n" + listOfState.getMax()));
                    } catch (StateException e) {

                        e.printStackTrace();
                    }

                }
                else if (inputString.equals("4")) {
                    System.out.println(listOfState.getAllNames());
                    String inputCountry = safeInput();
                    System.out.println(listOfState.getWithShort(inputCountry));
                }
                else if (inputString.equals("5")) {
                    nextInput = false;
                }
                else {
                    System.out.println("Zadaná neplatná volba - \"" + inputString + "\"! Vyber hodnotu z nabídky:");
                }
            }


        }

    public static String exportToFile(String out) throws StateException {
        String fileName = "outPut.txt";
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(out);
            return "Byl vytvořen soubor " + fileName;
        } catch (FileNotFoundException e) {
            throw  new StateException("Chyba při zápisu do souboru: " + fileName +"!");
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




