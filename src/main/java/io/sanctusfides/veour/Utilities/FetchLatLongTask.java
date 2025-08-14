//package io.sanctusfides.veour.Utilities;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import io.sanctusfides.veour.Models.Model;
//import javafx.concurrent.Task;
//import org.json.simple.parser.ParseException;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class FetchLatLongTask extends Task<Void> {
//
//    URI url;
//    HttpResponse<String> response;
//    String cityName;
//    String stateName;
//
//    public FetchLatLongTask(URI url,String cityName, String stateName) {
//        this.url = url;
//        this.response = null;
//        this.cityName = cityName;
//        this.stateName = stateName;
//    }
//
//    @Override
//    protected Void call() throws Exception {
//        HttpRequest request = null;
//        try {
//            request = HttpRequest
//                    .newBuilder(url)
//                    .header("accept", "application/json")
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        HttpClient client = HttpClient.newHttpClient();
//        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assert this.response != null;
//
//        try {
//            Object parsedResult = Model.getInstance().getApiDriver().convertHttpToObject(response);
//            JsonNode cityJson = Model.getInstance().getApiDriver().mapObjectToJsonNode(parsedResult, "results");
//            cityJson.forEach(local -> {
//                if (local.get("name") != null && local.get("admin1") != null) {
//                    String formattedCity = local.get("name").asText().toLowerCase().replace(" ","+");
//                    String formattedState = local.get("admin1").asText().toLowerCase().replace(" ","+");
//                    if (formattedCity.equals(this.cityName) && formattedState.equals(this.stateName)) {
//                        Model.getInstance().getApiDriver().setLatitude(local.get("latitude").asText());
//                        Model.getInstance().getApiDriver().setLongitude(local.get("longitude").asText());
//                    }
//                }
//            });
//        } catch (ParseException | JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    @Override
//    protected void succeeded() {
//        super.succeeded();
//        try {
//            Model.getInstance().getApiDriver().getWeather();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
