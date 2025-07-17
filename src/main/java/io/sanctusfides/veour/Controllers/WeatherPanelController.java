package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WeatherPanelController implements Initializable {

    public HBox weather_panel_main;

    VBox[] week;

    int dayCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        week = new VBox[7];
        try {
            showForecast();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showForecast() throws IOException {
        dayCount = 0;
        try {
            for (int i = 0; i < Model.getInstance().getWeeklyForecast().length; i++) {
//              Create the forecast cell GUI element from the FXML
                VBox forecastCell = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/ForecastCell.fxml")));

//              Add VBox element to array, which allows us to iterate through the individual days in this loop to set
//              other things like onClick. This is just a port for future options - currently not used
                week[i] = forecastCell;

//              Finally with data stored, we can now load the data onto the blank template to present the values to user
                Model.getInstance().getForecastCellFactory().buildForecastElement(forecastCell, Model.getInstance().getWeekDay(i), dayCount);

//              The last step is attach all the data-loaded elements onto the panel's main view
                weather_panel_main.getChildren().add(forecastCell);
                dayCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
