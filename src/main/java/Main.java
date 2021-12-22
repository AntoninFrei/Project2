

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
            //System.out.println("cccccc");
            System.out.println(json);

            JSONObject obj = new JSONObject(json);

            JSONObject readedData = obj.getJSONObject("rates");
            Iterator<String> keys = readedData.keys();

//            Boolean hasSuperReducedRate;
//            BigDecimal superReducedRate;

            StateList listOfState = new StateList();

            while(keys.hasNext()) {
                String countryShort = keys.next();
                //System.out.println(countryShort);
                //System.out.println(readedData.getJSONObject(countryShort).getString("country"));
                String country = readedData.getJSONObject(countryShort).getString("country");
                //System.out.println(readedData.getJSONObject(countryShort).getBigDecimal("standard_rate"));
                //System.out.println(readedData.getJSONObject(countryShort).getBigDecimal("reduced_rate"));

//                try {
//                    System.out.println(readedData.getJSONObject(countryShort).getBigDecimal("reduced_rate"));
//                }
//                catch (org.json.JSONException e) {
//                    System.out.println(readedData.getJSONObject(countryShort).getBoolean("reduced_rate"));
//                    System.out.println("Je to Boolean");
//                }

//                try {
//                    System.out.println("reduced_rate_alt " + readedData.getJSONObject(countryShort).getBigDecimal("reduced_rate_alt"));
//                }
//                catch (org.json.JSONException e) {
//                    System.out.println("reduced_rate_alt " + readedData.getJSONObject(countryShort).getBoolean("reduced_rate_alt"));
//                    System.out.println("Je to Boolean");
//                }
//
//                try {
//                    System.out.println(readedData.getJSONObject(countryShort).getBigDecimal("super_reduced_rate"));
//                }
//                catch (org.json.JSONException e) {
//                    System.out.println(readedData.getJSONObject(countryShort).getBoolean("super_reduced_rate"));
//                    System.out.println("Je to Boolean");
//                }

//                hasSuperReducedRate = getIsHas(readedData, "super_reduced_rate");
//                if (hasSuperReducedRate) {
//                    superReducedRate = readedData.getJSONObject(countryShort).getBigDecimal("super_reduced_rate");
//                } else {
//                    superReducedRate = new BigDecimal(0);
//                }


//                try {
//                    System.out.println(readedData.getJSONObject(countryShort).getBigDecimal("parking_rate"));
//                }
//                catch (org.json.JSONException e) {
//                    System.out.println(readedData.getJSONObject(countryShort).getBoolean("parking_rate"));
//                    System.out.println("Je to Boolean");
//                }

//                System.out.println("Pomoci funkce " + getIsHas(readedData, "super_reduced_rate"));

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

                //System.out.println("reduced rate alt: " + reducedRateAlt.getRate());

                State state = new State(country,countryShort, standardRate, reducedRate, reducedRateAlt,
                        superReducedRate, parkingRate);

                //Predicate<State> isInArray = States -> state.getName() == country;

                //List result = new ArrayList<StateList>(listOfState);

                //CollectionUtils.filter(result, o -> ((State) o).getName() == country);

                State findState = listOfState.getItemWithName(state);
                if (findState != null) {
                    findState.addShorCut(state.getFirstShortCut());
                    //listOfState.setAltShortCut(state);
                    System.out.println("Duplicita " + state.getName());
                } else {
                    listOfState.add(state);
                }




                //System.out.println(state.getDescrtion());


            }
            boolean nextInput = true;
//            String inputString = safeInput();
//            System.out.println("Zadano: " + inputString);
//            System.out.println(inputString.equals(new String("5")));
//            System.out.println(inputString.equals("5"));

            while(nextInput) {
                System.out.println("Vyber volbu:\n\t1 - Vytisky 3 země s nejnižší sazbou\n\t2 - Vytiskni 3 zeme " +
                        "z nejvyssi zakladní sazbou\n\t3 - soubor\n\t4 - zadej\n\t5 - konec");
                String inputString = safeInput();
                //switch (inputString) {
//                    case inputString.equals("1"):
//                        System.out.println(listOfState.getMin());
//                        break;
//                    case "2":
//                        System.out.println(listOfState.getMax());
//                        break;
//                    case '5':
//                        nextInput = false;

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



            //System.out.println("Všechny načtené státy:\n" + listOfState.toString(0, listOfState.length()));

            //System.out.println(listOfState.getMin());
            //System.out.println(listOfState.getMax());
            //System.out.println(listOfState.getAllNames());
            //System.out.println(listOfState.getSorted());

            //System.out.println("Všechny načtené státy:\n" + listOfState.toString(0, listOfState.length()));









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




