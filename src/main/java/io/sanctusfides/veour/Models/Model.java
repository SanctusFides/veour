package io.sanctusfides.veour.Models;


import io.sanctusfides.veour.Controllers.ForecastCellController;
import io.sanctusfides.veour.Exceptions.DayOutOfBoundsException;
import io.sanctusfides.veour.Factories.ForecastCellFactory;
import io.sanctusfides.veour.Views.ViewFactory;

import java.net.URI;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final ForecastCellFactory forecastCellFactory;
    private final ForecastCellController forecastCellController;
    private final APIDriver apiDriver;

    private final Forecast[] weeklyForecast;

    private Model() {
       this.viewFactory = new ViewFactory();
       this.forecastCellFactory = new ForecastCellFactory();
       this.forecastCellController = new ForecastCellController();
       this.apiDriver = new APIDriver();
       this.weeklyForecast = new Forecast[7];

       loadWeather();
    }

    private void loadWeather() {
        try {
            URI houstonTestURL = new URI("https://api.open-meteo.com/v1/forecast?latitude=29.7633&longitude=-95.3633" +
                    "&daily=temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,weather_code&current" +
                    "=temperature_2m,precipitation,relative_humidity_2m,apparent_temperature,weather_code,rain,showers" +
                    "&timezone=America%2FChicago&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch");

//          Load the weeks worth of forecasts
            Forecast[] weather = apiDriver.getWeather(houstonTestURL);
            for (int i = 0; i < weeklyForecast.length; i++) {
                setWeekDay(i,weather[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public Forecast[] getWeeklyForecast() {
        return weeklyForecast;
    }

    public Forecast getWeekDay(int dayCount) throws DayOutOfBoundsException {
        if(dayCount > 6 || dayCount < 0) {
            throw (new DayOutOfBoundsException("Day value used to retrieve forecast was not value"));
        }
        return weeklyForecast[dayCount];
    }

    public void setWeekDay(int dayCount,Forecast forecast) {
        weeklyForecast[dayCount] = forecast;
    }


    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    public ForecastCellController getForecastCellController() {
        return forecastCellController;
    }
    public APIDriver getApiDriver() {
        return apiDriver;
    }
    public ForecastCellFactory getForecastCellFactory(){
        return forecastCellFactory;
    }
}
