package io.sanctusfides.veour.Models;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class APIDriver {

    URI testURL;

    public APIDriver(){
        try {
            this.testURL = new URI("https://api.open-meteo.com/v1/forecast?latitude=29.7633&longitude=-95.3633&daily" +
                    "=temperature_2m_max,temperature_2m_min,rain_sum,showers_sum&current=temperature_2m,precipitation" +
                    ",relative_humidity_2m,apparent_temperature&timezone=America%2FChicago&forecast_days=1&" +
                    "wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse<String> getHoustonWeather() {
        HttpResponse<String> request = null;
        try {
            request = getWeather(testURL);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return request;
    }

//    This is commented out to work on one that will return the weather formatted in JSON instead
//    public HttpResponse<String> getWeather(URI url) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(url)
//                .timeout(Duration.of(10, ChronoUnit.SECONDS))
//                .GET()
//                .build();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        return response;
//    }

    public HttpResponse<String> getWeather(URI url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
