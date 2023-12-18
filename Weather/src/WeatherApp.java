import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherApp extends JFrame {
    JTextField zipCodeSearchBar;
    JTextField countryCodeSearchBar;

    JLabel morning;
    JLabel afternoon;
    JLabel night;
    JPanel hourly; // add morn, afternoon and night to this then this to frame
    JPanel temp; // add city name and temp to this
    JPanel clothes; // add bottom and top to this
    JLabel bottom;
    JPanel top;

    Font myFont = new Font("Courier New", Font.BOLD, 30);
    JFrame frame;


    JLabel temperatureLabel;
    JLabel clothingSuggestionLabel;

    WeatherApp() {
        frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,550);
        frame.setLayout(null);
        zipCodeSearchBar = new JTextField();
        zipCodeSearchBar.setBounds(15,5,350, 70);
        zipCodeSearchBar.setFont(myFont);
        zipCodeSearchBar.setForeground(Color.BLACK);
        zipCodeSearchBar.setEditable(false);
        zipCodeSearchBar.setBackground(Color.WHITE);
        countryCodeSearchBar = new JTextField();
        countryCodeSearchBar.setBounds(15,5,350, 70);
        countryCodeSearchBar.setFont(myFont);
        countryCodeSearchBar.setForeground(Color.BLACK);
        countryCodeSearchBar.setEditable(false);
        countryCodeSearchBar.setBackground(Color.WHITE);


        temperatureLabel = new JLabel(" Temperature: ");



        JButton fetchWeatherButton = new JButton("Fetch Weather");
        fetchWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Fetch weather data from API and update labels

                String zipCode = zipCodeSearchBar.getText();
                String countryCode = countryCodeSearchBar.getText();
                int currInfo = getWeatherData(zipCode, countryCode);



                // For demonstration purposes, let's assume weatherData contains the fetched data

                // Update temperature label
                temperatureLabel.setText("Temperature: " + weatherData.getTemperature() + "°C");

                // Update clothing suggestion label
                String clothingSuggestion = suggestClothing(weatherData.getTemperature());
                clothingSuggestionLabel.setText("Clothing suggestion: " + clothingSuggestion);
            }
        });

        add(fetchWeatherButton);
        add(temperatureLabel);
        add(clothingSuggestionLabel);

        pack();
        setLocationRelativeTo(null);
    }

    private Map getWeatherData(String zipCode, String countryCode) throws IOException {
        // TODO: Implement the code to fetch weather data from your preferred API
        String APIKey = "";
        //get lat and long for weather call
        URL latlogurl = new URL("http://api.openweathermap.org/geo/1.0/zip?zip="+zipCode+","+countryCode+"&appid="+APIKey);
        HttpURLConnection con = (HttpURLConnection) latlogurl.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response1 = new StringBuilder();
            String line;
            while((line = reader.readLine())!= null){
                response1.append(line);
            }
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response1.toString());

            // Extract relevant information
            double lat = jsonNode.get("lat").asDouble();
            double lon = jsonNode.get("lon").asDouble();
            URL weatherURL = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&limit=1&appid="+APIKey+"&units=imperial");
            HttpURLConnection con2 = (HttpURLConnection) weatherURL.openConnection();
            con.setRequestMethod("GET");
            int responseCode2 = con2.getResponseCode();
            if(responseCode2 == HttpURLConnection.HTTP_OK){
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
                StringBuilder response2 = new StringBuilder();
                String line2;
                while((line2 = reader.readLine())!= null){
                    response2.append(line2);
                }
                reader2.close();
                ObjectMapper objectMapper2 = new ObjectMapper();
                JsonNode jsonNode2 = objectMapper2.readTree(response2.toString());

                // Extract relevant information
                double temp = jsonNode.get("main").get("temp").asDouble();
                double feelsLike = jsonNode.get("main").get("feels_like").asDouble();
                double tempMin = jsonNode.get("main").get("temp_min").asDouble();
                double tempMax = jsonNode.get("main").get("temp_max").asDouble();
                String weather = jsonNode.get("weather").get("description").asText();
                double windSpeed = jsonNode.get("wind").get("speed").asDouble();

                Map<String, Object> currData = new HashMap<>();
                currData.put("Temp", temp);
                currData.put("Feels like", feelsLike);
                currData.put("Temp Min", tempMin);
                currData.put("Temp Max", tempMax);
                currData.put("Weather", weather);
                currData.put("Wind Speed", windSpeed);
                if(windSpeed > 10.8){
                    currData.put("Windy?", "yes");
                }
                return currData;
            }

        return null;
    }

    private String suggestClothing(double temperature) {
        // Simple clothing suggestion logic based on temperature
        if (temperature >= 25) {
            return "Wear light and breathable clothes.";
        } else if (temperature >= 15) {
            return "Wear a light jacket or sweater.";
        } else {
            return "Wear a warm jacket or coat.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherApp().setVisible(true);
            }
        });
    }
}

class WeatherData {
    private double temperature;

    public WeatherData(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
