package io.sanctusfides.veour.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;

public class Forecast {
    private StringProperty selectedTempUnit;
    private final DoubleProperty temp;
    private final DoubleProperty high;
    private final DoubleProperty low;
    private final DoubleProperty feelsLikeTemp;
    private final DoubleProperty humidty;
    private final DoubleProperty precipitation;
    private final DoubleProperty windSpeed;
    private final DoubleProperty windDirection;

    public Forecast(double temp, double high, double low, double feelsLikeTemp, double humidty, double precipitation,
                    double windSpeed, double windDirection) {
        this.temp = new SimpleDoubleProperty(this, "Temperature", temp);
        this.high = new SimpleDoubleProperty(this, "High", high);
        this.low = new SimpleDoubleProperty(this, "Low", low);
        this.feelsLikeTemp = new SimpleDoubleProperty(this, "FeelsLikeTemperature", feelsLikeTemp);
        this.humidty = new SimpleDoubleProperty(this, "Humidity", humidty);
        this.precipitation = new SimpleDoubleProperty(this, "Precipitation", precipitation);
        this.windSpeed = new SimpleDoubleProperty(this, "WindSpeed", windSpeed);
        this.windDirection = new SimpleDoubleProperty(this, "WindDirection", windDirection);
    }

    public String getSelectedTempUnit() {
        return selectedTempUnit.get();
    }

    public StringProperty selectedTempUnitProperty() {
        return selectedTempUnit;
    }

    public void setSelectedTempUnit(String selectedTempUnit) {
        this.selectedTempUnit.set(selectedTempUnit);
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

    public double getHumidty() {
        return humidty.get();
    }

    public DoubleProperty humidtyProperty() {
        return humidty;
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
