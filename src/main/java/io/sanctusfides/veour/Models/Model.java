package io.sanctusfides.veour.Models;


import io.sanctusfides.veour.Controllers.ForecastCellController;
import io.sanctusfides.veour.Exceptions.DayOutOfBoundsException;
import io.sanctusfides.veour.Factories.ForecastCellFactory;
import io.sanctusfides.veour.Views.ViewFactory;

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
