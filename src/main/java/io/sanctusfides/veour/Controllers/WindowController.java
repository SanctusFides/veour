package io.sanctusfides.veour.Controllers;

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

    VBox[] week;

//  VBox array above for Week is designed to allow you to pull out a specific day in (0 through 6) to alter if needed.
//  Array  is created and as each day element is loaded, it adds one into the week array so that we can retrieve it within
//  the same loop

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        week = new VBox[7];
        showForecast();
    }

    private void showForecast() {
        try {
            for (int i = 0; i < Model.getInstance().getWeeklyForecast().length; i++) {
//              Create the forecast cell GUI element from the FXML
                VBox forecastCell = FXMLLoader.load(getClass().getResource("/Fxml/ForecastCell.fxml"));
//                System.out.println(forecastCell.getChildren());
//              Add VBox element to array, which allows us to iterate through the individual days in this loop to set
//              other things like onClick. This is just a port for future options - currently not used
                week[i] = forecastCell;
//              Finally with data stored, we can now load the data onto the blank template to present the values to user
                Model.getInstance().getForecastCellFactory().buildForecastElement(forecastCell, Model.getInstance().getWeekDay(i));
//              The last step is attach all the data-loaded elements onto the panel's main view
                weather_panel_main.getChildren().add(forecastCell);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
