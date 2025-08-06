package io.sanctusfides.veour.Models;

import io.sanctusfides.veour.Exceptions.DayOutOfBoundsException;
import io.sanctusfides.veour.Views.ForecastCellFactory;
import io.sanctusfides.veour.Utilities.APIDriver;
import io.sanctusfides.veour.Views.ViewFactory;

import java.util.Collection;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final ForecastCellFactory forecastCellFactory;
    private final APIDriver apiDriver;
    private Collection<String> cities;

    private Forecast[] weeklyForecast;

    private Model() {
       this.viewFactory = new ViewFactory();
       this.forecastCellFactory = new ForecastCellFactory();
       this.apiDriver = new APIDriver();

       this.weeklyForecast = new Forecast[7];
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
    public void setWeeklyForecast(Forecast[] week) {
        this.weeklyForecast = week;
    }

    public Forecast getWeekDay(int dayCount) throws DayOutOfBoundsException {
        if(dayCount > 6 || dayCount < 0) {
            throw (new DayOutOfBoundsException("Day value used to retrieve forecast was not value"));
        }
        return weeklyForecast[dayCount];
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public APIDriver getApiDriver() {
        return apiDriver;
    }

    public ForecastCellFactory getForecastCellFactory(){
        return forecastCellFactory;
    }

    public void setCities(Collection<String> cities) {
        this.cities = cities;
    }
    public Collection<String> getCities() {
        return cities;
    }
}
