package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Forecast;
import io.sanctusfides.veour.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    @FXML
    private HBox weather_panel_main;
    @FXML
    private VBox day1;
    @FXML
    private VBox day2;
    @FXML
    private VBox day3;
    @FXML
    private VBox day4;
    @FXML
    private VBox day5;
    @FXML
    private VBox day6;
    @FXML
    private VBox day7;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadWeather();
    }

    private void loadWeather() {
        try {
            Forecast weather = Model.getInstance().getApiDriver().getHoustonWeather();
            for (int i = 0; i < 7; i++) {
                Model.getInstance().getWeeklyForecast()[i] = weather;
                VBox forecastCell = FXMLLoader.load(getClass().getResource("/Fxml/ForecastCell.fxml"));
                Model.getInstance().getForecastCellFactory().buildForecastElement(forecastCell, Model.getInstance().getWeekDay(i));

                weather_panel_main.getChildren().add(forecastCell);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
