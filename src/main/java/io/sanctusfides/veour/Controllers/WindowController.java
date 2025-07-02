package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Forecast;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    public HBox weather_panel_main;

    private Forecast weather;
    private ForecastCellController forecastCellController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
