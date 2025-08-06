package io.sanctusfides.veour.Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.sanctusfides.veour.Models.Forecast;
import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Views.ViewOptions;
import javafx.concurrent.Task;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FetchWeatherTask extends Task<HttpResponse<String>> {

    URI url;
    HttpResponse<String> response;

    public FetchWeatherTask(URI url) {
        this.url = url;
        this.response = null;
    }

    @Override
    protected HttpResponse<String> call() throws Exception {
        HttpRequest request = null;
        try {
            request = HttpRequest
                    .newBuilder(url)
                    .header("accept", "application/json")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return this.response;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        assert this.response != null;
        try {
            Object weatherJSON = Model.getInstance().getApiDriver().convertHttpToObject(response);
            Forecast[] forecast = Model.getInstance().getApiDriver().convertJsonToWeekForecast(weatherJSON);
            Model.getInstance().setWeeklyForecast(forecast);
            Model.getInstance().getViewFactory().getSelectedView().set(ViewOptions.WEATHER);
        } catch (ParseException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
