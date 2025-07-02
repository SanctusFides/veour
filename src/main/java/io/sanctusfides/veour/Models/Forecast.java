package io.sanctusfides.veour.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Forecast {

    private double temp;
    private double high;
    private double low;
    private double feelsLikeTemp;
    private double humidity;
    private double precipitation;

    private double windSpeed;
    private double windDirection;

//    public Forecast(double temp, double high, double low, double feelsLikeTemp, double humidity, double precipitation,
//                    double windSpeed, double windDirection) {
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
//        this.temp = new SimpleDoubleProperty(temp);
//        this.high = new SimpleDoubleProperty(high);
//        this.low = new SimpleDoubleProperty(low);
//        this.feelsLikeTemp = new SimpleDoubleProperty(feelsLikeTemp);
//        this.humidity = new SimpleDoubleProperty(humidity);
//        this.precipitation = new SimpleDoubleProperty(precipitation);
//        this.windSpeed = new SimpleDoubleProperty(windSpeed);
//        this.windDirection = new SimpleDoubleProperty(windDirection);
//    }

    public Forecast() {}

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(double feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "temp=" + temp +
                ", high=" + high +
                ", low=" + low +
                ", feelsLikeTemp=" + feelsLikeTemp +
                ", humidity=" + humidity +
                ", precipitation=" + precipitation +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                '}';
    }
}
