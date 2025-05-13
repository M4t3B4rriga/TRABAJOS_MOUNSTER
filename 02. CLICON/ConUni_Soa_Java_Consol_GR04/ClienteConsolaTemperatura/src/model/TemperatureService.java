package model;

import java.io.*;
import java.net.*;

public class TemperatureService{
     private final String BASE_URL = "http://10.9.6.253:8080/temperature-service";

    public boolean login(String username, String password) {
        try {
            URL url = new URL(BASE_URL + "/api/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }

            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }

    public String convertirCelsiusAFahrenheit(double celsius) {
        return realizarConversion("/api/temperature/toFahrenheit?celsius=" + celsius);
    }

    public String convertirFahrenheitACelsius(double fahrenheit) {
        return realizarConversion("/api/temperature/toCelsius?fahrenheit=" + fahrenheit);
    }

    private String realizarConversion(String endpoint) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return reader.readLine();
        } catch (Exception e) {
            return "Error en la conversión.";
        }
    }
}