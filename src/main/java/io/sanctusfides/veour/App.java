package io.sanctusfides.veour;

import io.sanctusfides.veour.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}