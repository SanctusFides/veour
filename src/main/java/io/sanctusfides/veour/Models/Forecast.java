package io.sanctusfides.veour.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Forecast {
    private final DoubleProperty temp;
    private final DoubleProperty high;
    private final DoubleProperty low;
    private final DoubleProperty feelsLikeTemp;
    private final DoubleProperty humidity;
    private final DoubleProperty precipitation;
    private final DoubleProperty windSpeed;
    private final DoubleProperty windDirection;

    public Forecast(double temp, double high, double low, double feelsLikeTemp, double humidity, double precipitation,
                    double windSpeed, double windDirection) {
        /**
        Not sure if I need these here to have a third value, I'm not using a database to need these to have name values for the bean
        this.temp = new SimpleDoubleProperty(this, "Temperature", temp);
        this.high = new SimpleDoubleProperty(this, "High", high);
        this.low = new SimpleDoubleProperty(this, "Low", low);
        this.feelsLikeTemp = new SimpleDoubleProperty(this, "FeelsLikeTemperature", feelsLikeTemp);
        this.humidity = new SimpleDoubleProperty(this, "Humidity", humidity);
        this.precipitation = new SimpleDoubleProperty(this, "Precipitation", precipitation);
        this.windSpeed = new SimpleDoubleProperty(this, "WindSpeed", windSpeed);
        this.windDirection = new SimpleDoubleProperty(this, "WindDirection", windDirection);
        * */

        this.temp = new SimpleDoubleProperty(temp);
        this.high = new SimpleDoubleProperty(high);
        this.low = new SimpleDoubleProperty(low);
        this.feelsLikeTemp = new SimpleDoubleProperty(feelsLikeTemp);
        this.humidity = new SimpleDoubleProperty(humidity);
        this.precipitation = new SimpleDoubleProperty(precipitation);
        this.windSpeed = new SimpleDoubleProperty(windSpeed);
        this.windDirection = new SimpleDoubleProperty(windDirection);
    }

    public double getTemp() {
        return temp.get();
    }

    public DoubleProperty tempProperty() {
        return temp;
    }

    public double getHigh() {
        return high.get();
    }

    public DoubleProperty highProperty() {
        return high;
    }

    public double getLow() {
        return low.get();
    }

    public DoubleProperty lowProperty() {
        return low;
    }

    public double getFeelsLikeTemp() {
        return feelsLikeTemp.get();
    }

    public DoubleProperty feelsLikeTempProperty() {
        return feelsLikeTemp;
    }

    public double getHumidity() {
        return humidity.get();
    }

    public DoubleProperty humidityProperty() {
        return humidity;
    }

    public double getPrecipitation() {
        return precipitation.get();
    }

    public DoubleProperty precipitationProperty() {
        return precipitation;
    }

    public double getWindSpeed() {
        return windSpeed.get();
    }

    public DoubleProperty windSpeedProperty() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection.get();
    }

    public DoubleProperty windDirectionProperty() {
        return windDirection;
    }
}
