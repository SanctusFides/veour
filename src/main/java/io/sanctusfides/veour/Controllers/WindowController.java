package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Forecast;
import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Views.ForecastCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
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

    private Forecast weather;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadWeather();
    }

    private void loadWeather() {
        try {
            weather = Model.getInstance().getApiDriver().getHoustonWeather();
            ArrayList<Forecast> week = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                VBox forecastCell = FXMLLoader.load(getClass().getResource("/Fxml/ForecastCell.fxml"));
                Model.getInstance().getForecastCellFactory().buildForecastElement(forecastCell, weather);
//                Model.getInstance().getForecastCellController().buildForecastElement(forecastCell,weather);

                weather_panel_main.getChildren().add(forecastCell);
            }
//            System.out.println(weather);
//            System.out.println(weather_panel_main.getChildren());
//            System.out.println(day1.getChildren().set);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
