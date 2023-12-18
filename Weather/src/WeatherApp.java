import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

    private Integer getWeatherData(String zipCode, String countryCode) {
        // TODO: Implement the code to fetch weather data from your preferred API
        String APIKey = "4d5bfce61bb0bfcb407e7e53221ed5d0";
        //get lat and long for weather call
        //response1 = 'http://api.openweathermap.org/geo/1.0/zip?zip=zipCode,countryCode&appid={API key}';
        String response1 = null;
        Double lat = Double.parseDouble(response1.split("\"lat\": ")[1].split(",")[0]);
        Double lon = Double.parseDouble(response1.split("\"lon\": ")[1].split(",")[0]);
        //http://api.openweathermap.org/geo/1.0/reverse?lat={lat}&lon={lon}&limit={limit}&appid={API key}
        int currData = 0;
        return currData; // Dummy data
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
