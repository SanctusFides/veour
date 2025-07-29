package io.sanctusfides.veour.Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sanctusfides.veour.Models.Forecast;
import io.sanctusfides.veour.Models.Model;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import static io.sanctusfides.veour.Utilities.Utility.mapWeatherCodeToWeatherDescr;

public class APIDriver {

    ObjectMapper mapper;

    String latitude;
    String longitude;

    public APIDriver() {
        this.mapper = new ObjectMapper();
    }


//  Takes network response and converts it into a json to use get methods on
    private Object convertHttpToObject(HttpResponse<String> response) throws ParseException {
        JSONParser parser = new JSONParser();
        return parser.parse(response.body());
    }

//  Takes the JSON and a string and retrieves the requested parent section from the JSON - needed for nested sections
    private JsonNode mapObjectToJsonNode(Object weather, String jsonParent) throws JsonProcessingException {
        JsonNode node = mapper.readTree(String.valueOf(weather));
        return node.get(jsonParent);
    }

//  Now that the request has been parsed for the relevant sections, the values are retrieved and returned attached to model
    private Forecast[] convertJsonToWeekForecast(Object weather) throws JsonProcessingException {
        JsonNode currentNode = mapObjectToJsonNode(weather, "current");
        JsonNode dailyNode = mapObjectToJsonNode(weather,"daily");

        JsonNode timeNode = dailyNode.get("time");
        JsonNode avgTempNode = dailyNode.get("temperature_2m_mean");
        JsonNode maxTempNode = dailyNode.get("temperature_2m_max");
        JsonNode minTempNode = dailyNode.get("temperature_2m_min");
        JsonNode feelsLikeTempNode = dailyNode.get("apparent_temperature_mean");
        JsonNode humidityNode = dailyNode.get("relative_humidity_2m_mean");
        JsonNode precipNode = dailyNode.get("precipitation_probability_mean");
        JsonNode weatherCodeNode = dailyNode.get("weather_code");
        JsonNode windDirection = dailyNode.get("wind_direction_10m_dominant");
        JsonNode windSpeed = dailyNode.get("wind_speed_10m_mean");

        return buildWeekForecast(
                currentNode, timeNode, avgTempNode,
                maxTempNode, minTempNode, feelsLikeTempNode,
                humidityNode,precipNode, weatherCodeNode,
                windDirection, windSpeed
        );
    }

//  Handles the actual building of forecast - adhering to the single responsibility principal
    private Forecast[] buildWeekForecast(JsonNode currentNode, JsonNode timeNode, JsonNode avgTempNode,
                                         JsonNode maxTempNode, JsonNode minTempNode, JsonNode feelsLikeTempNode,
                                         JsonNode humidtyNode, JsonNode precipNode, JsonNode weathCodeNode,
                                         JsonNode windDirection, JsonNode windSpeed) {
        Forecast[] week = new Forecast[7];
        for (int i = 0; i < 7; i++) {
            Forecast forecast = new Forecast();
            if (i == 0) {
                forecast.setTemp(currentNode.get("temperature_2m").asDouble());
                forecast.setFeelsLikeTemp(currentNode.get("apparent_temperature").asDouble());
                forecast.setHumidity(currentNode.get("relative_humidity_2m").asDouble());
                forecast.setPrecipitation(currentNode.get("precipitation").asDouble());
                forecast.setWindDirection(currentNode.get("wind_direction_10m").asInt());
                forecast.setWindSpeed(currentNode.get("wind_speed_10m").asInt());
                forecast.setWeatherCode(currentNode.get("weather_code").asText());
            } else {
                forecast.setTemp(avgTempNode.get(i).asDouble());
                forecast.setFeelsLikeTemp(feelsLikeTempNode.get(i).asDouble());
                forecast.setHumidity(humidtyNode.get(i).asDouble());
                forecast.setPrecipitation(precipNode.get(i).asDouble());
                forecast.setWindDirection(windDirection.get(i).asInt());
                forecast.setWindSpeed(windSpeed.get(i).asInt());
                forecast.setWeatherCode(weathCodeNode.get(i).asText());
            }
            forecast.setHigh(maxTempNode.get(i).asDouble());
            forecast.setLow(minTempNode.get(i).asDouble());
            forecast.setDate(LocalDate.parse(timeNode.get(i).asText()));

            forecast.setWeatherDescription(mapWeatherCodeToWeatherDescr(forecast.getWeatherCode()));

            week[i] = forecast;;
        }
        return week;
    }

    public Forecast[] getWeather() throws Exception {
        HttpResponse<String> request = null;

        RequestTask requestTask = new RequestTask(createWeatherAPIURL());
        Thread thread = new Thread(requestTask);
        thread.start();
        request = requestTask.call();
        assert request != null;

        Object weatherJSON = convertHttpToObject(request);
        Forecast[] forecast = convertJsonToWeekForecast(weatherJSON);
        Model.getInstance().setWeeklyForecast(forecast);
        return forecast;
    }

//  Set the lat and long variables in this class for the weather api to use when fetching the forecast
    public void setCityLatAndLong(String userCityInput) throws Exception {
        HttpResponse<String> request = null;
        if (!userCityInput.isEmpty()) {
            String userCityName = userCityInput.substring(0,userCityInput.indexOf(",")).toLowerCase().replace(" ","+");
            String userStateName = userCityInput.substring(userCityInput.indexOf(",")+1).trim().toLowerCase().replace(" ","+");
            URI cityUrl = createCityAPIURL(userCityName);

            RequestTask requestTask = new RequestTask(cityUrl);
            Thread thread = new Thread(requestTask);
            thread.start();
            request = requestTask.call();
            assert request != null;

            Object parsedResult = convertHttpToObject(request);
            JsonNode cityJson = mapObjectToJsonNode(parsedResult, "results");
            cityJson.forEach(local -> {
                if (local.get("name") != null && local.get("admin1") != null) {
                    String formattedCity = local.get("name").asText().toLowerCase().replace(" ","+");
                    String formattedState = local.get("admin1").asText().toLowerCase().replace(" ","+");
                    if (formattedCity.equals(userCityName) && formattedState.equals(userStateName)) {
                        setLatitude(local.get("latitude").asText());
                        setLongitude(local.get("longitude").asText());
                    }
                }
            });
        }
    }

    private URI createWeatherAPIURL() throws URISyntaxException {
        return new URI("https://api.open-meteo.com/v1/forecast?latitude=" +
                latitude+"&longitude="+longitude+
                "&daily=temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,weather_code,temperature_2m_mean," +
                "precipitation_probability_mean,relative_humidity_2m_mean,apparent_temperature_mean," +
                "wind_direction_10m_dominant,wind_speed_10m_mean&current=temperature_2m,precipitation," +
                "relative_humidity_2m,apparent_temperature,weather_code,rain,showers,wind_speed_10m,wind_direction_10m" +
                "&timezone=America%2FChicago&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch");
    }

    private URI createCityAPIURL(String cityName) throws URISyntaxException {
        return new URI("https://geocoding-api.open-meteo.com/v1/search?name="
                + cityName + "&count=100&language=en&format=json");
    }

    private void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private void setLongitude(String longitude) {
        this.longitude = longitude;
    }

//    private HttpResponse<String> handleRequest(URI url) throws Exception {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(url)
//                .timeout(Duration.of(10, ChronoUnit.SECONDS))
//                .GET()
//                .build();
//        HttpClient client = HttpClient.newHttpClient();
//        return client.send(request, HttpResponse.BodyHandlers.ofString());
//    }
}
