package io.sanctusfides.veour.Factories;

import io.sanctusfides.veour.Models.Forecast;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ForecastCellFactory{

    public ForecastCellFactory() {

    }

    public void buildForecastElement(VBox cell, Forecast forecast) {
//        System.out.println(cell.getChildren());
//        System.out.println(forecast);

        Label dayLbl = (Label) cell.getChildren().get(0);
        dayLbl.setText(forecast.getDayNameString());

        // ToDo get svg files to use here
        ImageView imgView = (ImageView) cell.getChildren().get(2);

        Label tempLbl = (Label) cell.getChildren().get(3);
        tempLbl.setText(forecast.getTempString());

        VBox highLowFeelsLikeTempBox = (VBox) cell.getChildren().get(5);
        VBox precipHumidWindBox = (VBox) cell.getChildren().get(6);

        HBox highBox = (HBox) highLowFeelsLikeTempBox.getChildren().getFirst();
        Label highLbl = (Label) highBox.getChildren().get(1);
        highLbl.setText(forecast.getHighTempString());

        HBox lowBox = (HBox) highLowFeelsLikeTempBox.getChildren().get(1);
        Label lowLbl = (Label) lowBox.getChildren().get(1);
        lowLbl.setText(forecast.getLowTempString());

        HBox feelsBox = (HBox) highLowFeelsLikeTempBox.getChildren().get(2);
        Label feelsLabel = (Label) feelsBox.getChildren().get(1);
        feelsLabel.setText(forecast.getFeelsLikeTempString());


        HBox precipBox = (HBox) precipHumidWindBox.getChildren().getFirst();
        Label precipLbl = (Label) precipBox.getChildren().get(1);
        precipLbl.setText(forecast.getPrecipitationString());

        HBox humidityBox = (HBox) precipHumidWindBox.getChildren().get(1);
        Label humidityLbl = (Label) humidityBox.getChildren().get(1);
        humidityLbl.setText(forecast.getHumidityString());

        HBox windBox = (HBox) precipHumidWindBox.getChildren().get(2);
        Label windLbl = (Label) windBox.getChildren().get(1);
        windLbl.setText("TBD");

//      TODO - CREATE A CONSTANTS FILE FOR DAY, WEEK, 2 WEEKS, ETC

//        Label highLbl = (Label) cell.getChildren()

//        temp_lbl.setText(String.valueOf(forecast.getTemp()));
//        day_lbl.setText(LocalDateTime.parse(forecast.getDate()).getDayOfWeek().toString());
//        high_lbl.setText(String.valueOf(forecast.getHigh()));
//        low_lbl.setText(String.valueOf(forecast.getLow()));
//        feels_like_lbl.setText(String.valueOf(forecast.getFeelsLikeTemp()));
//        precip_lbl.setText(String.valueOf(forecast.getPrecipitation()));
//        humidity_lbl.setText(String.valueOf(forecast.getHumidity()));
//        wind_lbl.setText(String.valueOf(forecast.getWindSpeed()));

    }
}
