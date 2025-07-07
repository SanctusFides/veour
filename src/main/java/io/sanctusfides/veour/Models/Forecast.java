package io.sanctusfides.veour.Models;

import java.time.LocalDateTime;

public class Forecast {

    private double temp;
    private double high;
    private double low;
    private double feelsLikeTemp;
    private double humidity;
    private double precipitation;
    private String date;

    private double windSpeed;
    private double windDirection;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayNameString() {
        return LocalDateTime.parse(date).getDayOfWeek().toString();
    }
    public String getTempString() {
        return String.valueOf(temp)+"°";
    }
    public String getHighTempString() {
        return String.valueOf(high)+"°";
    }
    public String getLowTempString() {
        return String.valueOf(low)+"°";
    }
    public String getFeelsLikeTempString() {
        return String.valueOf(feelsLikeTemp)+"°";
    }
    public String getHumidityString() {
        return String.valueOf(humidity)+"%";
    }
    public String getPrecipitationString() {
        return String.valueOf(precipitation)+'"';
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
