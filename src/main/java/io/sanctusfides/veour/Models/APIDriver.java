package io.sanctusfides.veour.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class APIDriver {

    ObjectMapper mapper;

    public APIDriver(){
        this.mapper = new ObjectMapper();
    }

// Makes the network request to the API url and passes along the response to be converted into JSON for parsing
    private HttpResponse<String> handleRequest(URI url) throws Exception {
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
    private Forecast[] convertJsonToWeekForecast(Object weather) throws JsonProcessingException {
        JsonNode currentNode = mapToJsonNodes(weather, "current");
        JsonNode dailyNode = mapToJsonNodes(weather,"daily");
        return buildWeekForecast(currentNode,dailyNode);
    }

// Handles the actual building of forecast - adhering to the single responsibility principal
    private Forecast[] buildWeekForecast(JsonNode currentNode, JsonNode dailyNode) {
        Forecast[] week = new Forecast[7];
        for (int i = 0; i < 7; i++) {
            Forecast forecast = new Forecast();
            forecast.setTemp(currentNode.get("temperature_2m").asDouble());
            forecast.setHigh(dailyNode.get("temperature_2m_max").get(0).asDouble());
            forecast.setLow(dailyNode.get("temperature_2m_min").get(0).asDouble());
            forecast.setFeelsLikeTemp(currentNode.get("apparent_temperature").asDouble());
            forecast.setHumidity(currentNode.get("relative_humidity_2m").asDouble());
            forecast.setWindSpeed(0.0);
            forecast.setWindDirection(0.0);
            forecast.setDate(LocalDate.parse(dailyNode.get("time").get(0).asText()));

            week[i] = forecast;
        }
        return week;
    }
//    Old method for the one above, this one worked when it was single day tests
//    private Forecast buildForecast(JsonNode currentNode, JsonNode dailyNode) {
//        Forecast forecast = new Forecast();
//        forecast.setTemp(currentNode.get("temperature_2m").asDouble());
//        forecast.setHigh(dailyNode.get("temperature_2m_max").get(0).asDouble());
//        forecast.setLow(dailyNode.get("temperature_2m_min").get(0).asDouble());
//        forecast.setFeelsLikeTemp(currentNode.get("apparent_temperature").asDouble());
//        forecast.setHumidity(currentNode.get("relative_humidity_2m").asDouble());
//        forecast.setWindSpeed(0.0);
//        forecast.setWindDirection(0.0);
//        forecast.setDate(LocalDate.parse(dailyNode.get("time").get(0).asText()));
//        return forecast;
//    }

    public Forecast[] getWeather(URI url) throws ParseException, JsonProcessingException {
        HttpResponse<String> request = null;
        try {
            request = handleRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert request != null;
        Object weatherJSON = convertHttpToJson(request);
        return convertJsonToWeekForecast(weatherJSON);
    }

    public String getCityName() {
        HttpResponse<String> request = null;
        try {
            URI cityUrl = URI.create("https://geocoding-api.open-meteo.com/v1/search?name=Houston&count=10&language=en&format=json");
            request = handleRequest(cityUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assert request != null;
//        System.out.println(request.body());
        return "hello";
    }
}
