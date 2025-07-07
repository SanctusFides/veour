package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Forecast;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ForecastCellController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    public void buildForecastElement(VBox cell, Forecast forecast) {
//        day_lbl = new Label(LocalDateTime.parse(forecast.getDate()).getDayOfWeek().toString());
//        day_lbl.setStyle("day_lbl ");
//        day_lbl.setPadding(new Insets(15,0,15,0));
//
////        temp_lbl. = new Label(String.valueOf(forecast.getTemp()));
//        temp_lbl.setText(String.valueOf(forecast.getTemp()));
//        temp_lbl.setStyle("temp_lbl");
//
//        cell.getChildren().set(0,day_lbl);
//
////        temp_lbl.setText(String.valueOf(forecast.getTemp()));
////        day_lbl.setText(LocalDateTime.parse(forecast.getDate()).getDayOfWeek().toString());
////        high_lbl.setText(String.valueOf(forecast.getHigh()));
////        low_lbl.setText(String.valueOf(forecast.getLow()));
////        feels_like_lbl.setText(String.valueOf(forecast.getFeelsLikeTemp()));
////        precip_lbl.setText(String.valueOf(forecast.getPrecipitation()));
////        humidity_lbl.setText(String.valueOf(forecast.getHumidity()));
////        wind_lbl.setText(String.valueOf(forecast.getWindSpeed()));
//
//    }

}
