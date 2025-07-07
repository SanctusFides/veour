package io.sanctusfides.veour.Models;


import io.sanctusfides.veour.Controllers.ForecastCellController;
import io.sanctusfides.veour.Views.ForecastCellFactory;
import io.sanctusfides.veour.Views.ViewFactory;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final ForecastCellFactory forecastCellFactory;
    private final ForecastCellController forecastCellController;
    private final APIDriver apiDriver;

    private Model() {
       this.viewFactory = new ViewFactory();
       this.forecastCellFactory = new ForecastCellFactory();
       this.forecastCellController = new ForecastCellController();
       this.apiDriver = new APIDriver();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
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
