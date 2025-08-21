package io.sanctusfides.veour;

import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Utilities.Utility;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage){
        Utility loader = new Utility();
        loader.loadCityList();
        Model.getInstance().getViewFactory().showWindow();
//        loader.loadDBList();
//        Model.getInstance().getSqLiteDriver().buildDB();
//        Model.getInstance().getSqLiteDriver().readDB();
//        Model.getInstance().getSqLiteDriver().deleteDB();
//        System.exit(0);
    }


    public static void main(String[] args) {
        launch();
    }
}