package com.company.weather;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherController {
    @FXML
    private Text feels;

    @FXML
    private Text maximum;

    @FXML
    private Text minimum;

    @FXML
    private Text pressure;

    @FXML
    private Button searchingButton;

    @FXML
    private TextField searchingLabel;

    @FXML
    private Text temperature;

    @FXML
    void initialize() {
        searchingButton.setOnAction(actionEvent -> {
            String getUserCity = searchingLabel.getText().trim();
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=30a71b8622c66fbacca4e7a569357730");
            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                temperature.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp"));
                feels.setText("Feels: " + obj.getJSONObject("main").getDouble("feels_like"));
                minimum.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min"));
                maximum.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max"));
                pressure    .setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure"));
            }
            System.out.println(output);
        });
    }

    private static String getUrlContent(String urlAddress) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            URLConnection connection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Invalid city");
            e.printStackTrace();
        }
        return content.toString();
    }

}