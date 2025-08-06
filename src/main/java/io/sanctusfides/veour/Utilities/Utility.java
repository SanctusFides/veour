package io.sanctusfides.veour.Utilities;

import io.sanctusfides.veour.Models.Model;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public final class Utility {

    public static String getWeatherImageFilePath(String code) {
        return switch (code) {
            case "0" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/sun.png")).toString();
            case "1", "2" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/partly-cloudy.png")).toString();
            case "3" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/cloudy.png")).toString();
            case "45", "48" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/fog.png")).toString();
            case "51", "53", "55", "56", "57" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/drizzle.png")).toString();
            case "61", "63", "65", "66", "67" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/rain.png")).toString();
            case "71", "73", "75", "77" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/snow.png")).toString();
            case "80", "81", "82" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/rain.png")).toString();
            case "85", "86" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/snow.png")).toString();
            case "95", "96", "99" -> Objects.requireNonNull(Utility.class.getResource("/Images/Weather/storm.png")).toString();
            default -> "error";
        };
    }

    public static Image getWindDirectionArrowImage(int code) {
        Image image = null;
        if (code < 10) {
            image = new Image(Utility.class.getResource("/Images/Arrows/south.png").toString());
        } else if (code < 80) {
            image = new Image(Utility.class.getResource("/Images/Arrows/southwest.png").toString());
        } else if (code < 100) {
            image = new Image(Utility.class.getResource("/Images/Arrows/west.png").toString());
        } else if (code < 170) {
            image = new Image(Utility.class.getResource("/Images/Arrows/northwest.png").toString());
        } else if (code < 190) {
            image = new Image(Utility.class.getResource("/Images/Arrows/north.png").toString());
        } else if (code < 260) {
            image = new Image(Utility.class.getResource("/Images/Arrows/northeast.png").toString());
        } else if (code < 280) {
            image = new Image(Utility.class.getResource("/Images/Arrows/east.png").toString());
        } else if (code < 350) {
            image = new Image(Utility.class.getResource("/Images/Arrows/southeast.png").toString());
        } else if (code <= 360 ){
            image = new Image(Utility.class.getResource("/Images/Arrows/south.png").toString());
        } else {
            image = new Image(Utility.class.getResource("/Images/error.png").toString());
        }
//        if (code < 20) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/north.png").toString());
//        } else if (code < 70) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/northeast.png").toString());
//        } else if (code < 110) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/east.png").toString());
//        } else if (code < 160) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/southeast.png").toString());
//        } else if (code < 200) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/south.png").toString());
//        } else if (code < 250) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/southwest.png").toString());
//        } else if (code < 290) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/west.png").toString());
//        } else if (code < 340) {
//            image = new Image(Utility.class.getResource("/Images/Arrows/northwest.png").toString());
//        } else if (code <= 360 ){
//            image = new Image(Utility.class.getResource("/Images/Arrows/north.png").toString());
//        } else {
//            image = new Image(Utility.class.getResource("/Images/error.png").toString());
//        }
        return image;
    }

    public static String mapWeatherCodeToWeatherDescr(String code) {
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

    public void loadCityList() {
        try {
            List<String> cities = Files.readAllLines(Paths.get(Objects.requireNonNull(getClass().getResource("/Files/locations.txt")).toURI()));
            Model.getInstance().setCities(cities);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
