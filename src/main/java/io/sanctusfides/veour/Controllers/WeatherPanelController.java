package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Forecast;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class WeatherPanelController implements Initializable {

    public ListView<ListCell<Forecast>> forecast_listview;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forecast_listview = new ListView<>();
    }

    public void createWeek() {

    }
}
