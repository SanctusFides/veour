package io.sanctusfides.veour.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class APIDriver {

    URI testURL;
    ObjectMapper mapper;

    public APIDriver(){
        // Creating a test url temporarily to work on formatting
        try {
            this.testURL = new URI("https://api.open-meteo.com/v1/forecast?latitude=29.7633&longitude=-95.3633&daily=temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,weather_code&current=temperature_2m,precipitation,relative_humidity_2m,apparent_temperature,weather_code,rain,showers&timezone=America%2FChicago&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        this.mapper = new ObjectMapper();
    }

// Makes the network request to the API url and passes along the response to be converted into JSON for parsing
    private HttpResponse<String> requestWeather(URI url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

// Takes network response and converts it into a json to use get methods on
    private Object convertHttpToJson(HttpResponse<String> response) throws ParseException {
        JSONParser parser = new JSONParser();
        return parser.parse(response.body());
    }

// Takes the JSON and a string and retrieves the requested parent section from the JSON - needed for nested sections
    private JsonNode mapToJsonNodes(Object weather,String jsonParent) throws JsonProcessingException {
        JsonNode node = mapper.readTree(String.valueOf(weather));
        return node.get(jsonParent);
    }

// Now that the request has been parsed for the relevant sections, the values are retrieved and returned attached to model
    private Forecast convertJsonToForecast(Object weather) throws JsonProcessingException {
        JsonNode currentNode = mapToJsonNodes(weather, "current");
        JsonNode dailyNode = mapToJsonNodes(weather,"daily");
        return buildForecast(currentNode,dailyNode);
    }

// Handles the actual building of forecast - adhering to the single responsibility principal
    private Forecast buildForecast(JsonNode currentNode, JsonNode dailyNode) {
        int dayCount = 0;
        Forecast forecast = new Forecast();
        forecast.setTemp(currentNode.get("temperature_2m").asDouble());
        forecast.setHigh(dailyNode.get("temperature_2m_max").get(dayCount).asDouble());
        forecast.setLow(dailyNode.get("temperature_2m_min").get(dayCount).asDouble());
        forecast.setFeelsLikeTemp(currentNode.get("apparent_temperature").asDouble());
        forecast.setHumidity(currentNode.get("relative_humidity_2m").asDouble());
        forecast.setWindSpeed(0.0);
        forecast.setWindDirection(0.0);
        forecast.setDate(LocalDate.parse(dailyNode.get("time").get(0).asText()));
        return forecast;
    }
//    METHOD BELOW IS FOR OUR SINGLE DAY TEST, ONE ABOVE IS FOR TAKING IN DAY COUNT TO INC THROUGH LISTS WITH
//    private Forecast buildForecast(JsonNode currentNode, JsonNode dailyNode) {
//        Forecast forecast = new Forecast();
//        forecast.setTemp(currentNode.get("temperature_2m").asDouble());
//        forecast.setHigh(dailyNode.get("temperature_2m_max").get(0).asDouble());
//        forecast.setLow(dailyNode.get("temperature_2m_min").get(0).asDouble());
//        forecast.setFeelsLikeTemp(currentNode.get("apparent_temperature").asDouble());
//        forecast.setHumidity(currentNode.get("relative_humidity_2m").asDouble());
//        forecast.setWindSpeed(0.0);
//        forecast.setWindDirection(0.0);
//        forecast.setDate(currentNode.get("time").asText());
//        return forecast;
//    }


// Used just for fetching the static URL for testing
    public Forecast getHoustonWeather() throws ParseException, JsonProcessingException {
        HttpResponse<String> request = null;
        try {
            request = requestWeather(testURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert request != null;
        Object weatherJSON = convertHttpToJson(request);
        return convertJsonToForecast(weatherJSON);
    }
}
