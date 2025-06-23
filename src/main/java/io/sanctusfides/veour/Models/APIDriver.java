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
            this.testURL = new URI("https://api.open-meteo.com/v1/forecast?latitude=29.7633&longitude=-95.3633&current=" +
                    "temperature_2m,precipitation,relative_humidity_2m,apparent_temperature,weather_code,rain,cloud_cover," +
                    "showers,wind_speed_10m,wind_direction_10m&wind_speed_unit=mph&temperature_unit=fahrenheit" +
                    "&precipitation_unit=inch");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void printWeather() {
        try {
            getHoustonWeather(testURL);
        } catch (InterruptedException | IOException e2) {
            e2.printStackTrace();
        }
    }

    public HttpRequest getHoustonWeather(URI url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return request;
    }
}
