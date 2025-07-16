package io.sanctusfides.veour.Views;

import io.sanctusfides.veour.Models.Forecast;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static io.sanctusfides.veour.Utilities.Utility.*;


public class ForecastCellFactory{

    /* The Get indexes for ForecastCell
     *  0:daylabel, 1:line, 2:weather svg, 3: weather temp, 4:day status, 5:weather description, 6:line,
     *  7:highLowFeelsLikeTempBox, 8: precipHumidWindBox
     */

    public void buildForecastElement(VBox cell, Forecast forecast, int dayCount) {
        // Sets the border on the right side of each sell as long as they aren't the last one
        if (dayCount < 6) {
            cell.setStyle("-fx-border-style: hidden solid hidden hidden; -fx-border-color: white; -fx-border-width: 1;");
        }
        Label dayLbl = (Label) cell.getChildren().get(0);
        dayLbl.setText(forecast.getDayNameString());

        ImageView weatherImgView = (ImageView) cell.getChildren().get(2);
        Image weatherImg = new Image(getWeatherImageFilePath(forecast.getWeatherCode()));
        weatherImgView.setImage(weatherImg);

        Label tempLbl = (Label) cell.getChildren().get(3);
        tempLbl.setText(forecast.getTempString());

        Label dayStatus = (Label) cell.getChildren().get(4);
        if (dayCount == 0) {
            dayStatus.setText("Current");
        } else {
            dayStatus.setText("Average");
        }

        Label weatherDescription = (Label) cell.getChildren().get(5);
        weatherDescription.setText(forecast.getWeatherDescription());


        VBox highLowFeelsLikeTempBox = (VBox) cell.getChildren().get(7);
        VBox precipHumidWindBox = (VBox) cell.getChildren().get(8);

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
        ImageView windImgView = (ImageView) windBox.getChildren().get(1);
        Image windImage = getWindDirectionArrowImage(forecast.getWindDirection());
        windImgView.setImage(windImage);

        Label windLbl = (Label) windBox.getChildren().get(2);
        windLbl.setText(forecast.getWindSpeedString());
    }
}