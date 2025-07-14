package io.sanctusfides.veour.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ForecastCellController implements Initializable {

    @FXML
    private Label forecast_active_status_lbl;
    @FXML
    private Label weather_descr_lbl;
    @FXML
    private Label day_lbl;
    @FXML
    private ImageView weather_img;
    @FXML
    private Label temp_lbl;
    @FXML
    private Label high_lbl;
    @FXML
    private Label low_lbl;
    @FXML
    private Label feels_like_lbl;
    @FXML
    private Label precip_lbl;
    @FXML
    private Label humidity_lbl;
    @FXML
    private Label wind_lbl;
    @FXML
    private ImageView wind_img;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
