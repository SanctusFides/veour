package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Forecast;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ForecastCellController implements Initializable {

    public Label day_lbl;
    public ImageView weather_img;
    public Label temp_lbl;
    public Label high_lbl;
    public Label low_lbl;
    public Label feels_like_lbl;
    public Label precip_lbl;
    public Label humidity_lbl;
    public Label wind_lbl;

    private final Forecast forecast;

    public ForecastCellController(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
