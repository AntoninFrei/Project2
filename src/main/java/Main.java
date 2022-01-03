import com.engeto.project2.State;
import com.engeto.project2.StateException;
import com.engeto.project2.StateList;
import com.engeto.project2.TaxRate;
import org.json.JSONObject;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

import static com.engeto.project2.ReadInput.safeInput;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //String json = ClientBuilder.newClient().target("https://euvatrates.com/rates.json").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        //System.out.println("test");
        //System.out.println(json);

        String address = "https://euvatrates.com/rates.json";
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(address)).GET().build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        //System.out.println("--------------------------------------------");
        //System.out.println(json);
        String json = (String) httpResponse.body();

        JSONObject obj = new JSONObject(json);
        //JSONObject obj = new JSONObject(httpResponse.body());

        JSONObject readedData = obj.getJSONObject("rates");
        Iterator<String> keys = readedData.keys();


        StateList listOfState = new StateList();

        while (keys.hasNext()) {
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


            State state = new State(country, countryShort, standardRate, reducedRate, reducedRateAlt,
                    superReducedRate, parkingRate);


            State findState = listOfState.getItemWithName(state);
            if (findState != null) {
                findState.addShorCut(state.getFirstShortCut());
                System.out.println("Duplicita " + state.getName());
            } else {
                listOfState.add(state);
            }


        }
        boolean nextInput = true;
        while (nextInput) {
            System.out.println("Vyber volbu:\n\t1 - Vytiskni 3 země s nejnižší základní sazbou\n\t2 - Vytiskni 3 země " +
                    "s nejvyšši základní sazbou\n\t3 - Ulož to souboru 3 země s nejnižší základní sazbou a 3 země " +
                    "s nejvyšší základní sazbou\n\t4 - Vypiš zemi podle zadaná zkratky\n\t5 - Konec");
            String inputString = safeInput();
            if (inputString.equals("1")) {
                System.out.println(listOfState.getMin());
            } else if (inputString.equals("2")) {
                System.out.println(listOfState.getMax());
            } else if (inputString.equals("3")) {
                try {
                    System.out.println(exportToFile(listOfState.getMin() + "\n\n" + listOfState.getMax()));
                } catch (StateException e) {

                    e.printStackTrace();
                }

            } else if (inputString.equals("4")) {
                System.out.println(listOfState.getAllNames());
                String inputCountry = safeInput();
                System.out.println(listOfState.getWithShort(inputCountry));
            } else if (inputString.equals("5")) {
                nextInput = false;
            } else {
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
            throw new StateException("Chyba při zápisu do souboru: " + fileName + "!");
        }
    }

}




