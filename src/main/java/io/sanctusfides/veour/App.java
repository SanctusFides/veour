package io.sanctusfides.veour;

import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Utilities.Utility;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Utility loader = new Utility();
        loader.loadCityList();
        Model.getInstance().getViewFactory().showWindow();

//        TODO: comment this out once exe is working
////        loader.loadDBList();
////        Model.getInstance().getSqLiteDriver().buildDB();
////        Model.getInstance().getSqLiteDriver().readDB();
////        Model.getInstance().getSqLiteDriver().deleteDB();
////        System.exit(0);
    }

}