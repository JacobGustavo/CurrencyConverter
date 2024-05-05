import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHandler {

    public double getConversionRate(String baseCurrency, String destinationCurrency) throws IOException {
        String apiKey = "ac62974d827b16e4c8d101ec";
        String apiUrl = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, baseCurrency, destinationCurrency);

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            ConversionResponse conversionResponse = gson.fromJson(response.toString(), ConversionResponse.class);
            return conversionResponse.getConversionRate();
        } else {
            System.out.println("Erro ao obter resposta da API. Código de status: " + responseCode);
            return -1;
        }
    }
}

class ConversionResponse {
    private String result;
    private double conversion_rate;

    public double getConversionRate() {
        return conversion_rate;
    }
}