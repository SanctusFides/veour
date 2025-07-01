package io.sanctusfides.veour.Models;


import io.sanctusfides.veour.Views.ViewFactory;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final APIDriver apiDriver;

    private Model() {
       this.viewFactory = new ViewFactory();
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
    public APIDriver getApiDriver() {
        return apiDriver;
    }
}
