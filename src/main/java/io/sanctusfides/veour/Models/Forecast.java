package io.sanctusfides.veour.Models;

import java.time.LocalDate;

public class Forecast {

    private double temp;
    private double high;
    private double low;
    private double feelsLikeTemp;
    private double humidity;
    private double precipitation;
    private LocalDate date;

    private String weatherCode;
    private String weatherDescription;

    private int windSpeed;
    private int windDirection;

    public Forecast() {}

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setFeelsLikeTemp(double feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getWeatherDescription() { return weatherDescription; }

    public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }

    public int getWindDirection() {
        return windDirection;
    }

    public String getDayNameString() {
        return date.getDayOfWeek().toString();
    }
    public String getTempString() {
        return temp +"°";
    }
    public String getHighTempString() {
        return high +"°";
    }
    public String getLowTempString() {
        return low +"°";
    }
    public String getFeelsLikeTempString() {
        return feelsLikeTemp +"°";
    }
    public String getHumidityString() {
        return humidity +"%";
    }
    public String getPrecipitationString() {
        return precipitation +"%";
    }

    public String getWindSpeedString() {
        return windSpeed +" mp/h";
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
                ", date=" + date +
                ", weatherCode='" + weatherCode + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                '}';
    }
}
