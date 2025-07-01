package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.APIDriver;
import io.sanctusfides.veour.Models.Forecast;
import io.sanctusfides.veour.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    public HBox weather_panel_main;

    private HttpResponse<String> weather;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weather = Model.getInstance().getApiDriver().getHoustonWeather();
        System.out.println(weather.body());
        Forecast forecast = new Forecast();
    }
}
