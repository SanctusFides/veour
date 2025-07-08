package io.sanctusfides.veour.Factories;

import io.sanctusfides.veour.Models.Forecast;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ForecastCellFactory{

    public ForecastCellFactory() {

    }
/* The Get indexes for ForecastCell
*  0:daylabel, 1:line, 2:weather svg, 3: weather temp, 4:day status, 5:weather description, 6:line,
*  7:highLowFeelsLikeTempBox, 8: precipHumidWindBox
*/

    public void buildForecastElement(VBox cell, Forecast forecast, int dayCount) {
        Label dayLbl = (Label) cell.getChildren().get(0);
        dayLbl.setText(forecast.getDayNameString());

        // ToDo get svg files to use here
        ImageView imgView = (ImageView) cell.getChildren().get(2);

        Label tempLbl = (Label) cell.getChildren().get(3);
        tempLbl.setText(forecast.getTempString());

        Label dayStatus = (Label) cell.getChildren().get(4);
        if (dayCount == 0) {
            dayStatus.setText("Current");
        } else {
            dayStatus.setText("Average");
        }


        Label weatherDescription = (Label) cell.getChildren().get(5);
        weatherDescription.setText(convertWeatherCode(forecast.getWeatherCode()));


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
        Label windLbl = (Label) windBox.getChildren().get(1);
        windLbl.setText("TBD");
    }

    private String convertWeatherCode(String code) {
        return switch (code) {
            case "0" -> "Clear Sky";
            case "1" -> "Mostly Clear";
            case "2" -> "Partly Cloudy";
            case "3" -> "Overcast";
            case "45", "48" -> "Fog";
            case "51", "53", "55" -> "Drizzle";
            case "56", "57" -> "Freezing Drizzle";
            case "61" -> "Slight Rain";
            case "63" -> "Moderate Rain";
            case "65" -> "Heavy Rain";
            case "66", "67" -> "Freezing Rain";
            case "71" -> "Slight Snow";
            case "73" -> "Moderate Snow";
            case "75" -> "Heavy Snow";
            case "77" -> "Snow Grains";
            case "80" -> "Slight Showers";
            case "81" -> "Moderate Showers";
            case "82" -> "Heavy Showers";
            case "85", "86" -> "Snow Showers";
            case "95" -> "Thunderstorms";
            case "96", "99" -> "Hail Thunderstorms";
            default -> "error";
        };
    }
}
