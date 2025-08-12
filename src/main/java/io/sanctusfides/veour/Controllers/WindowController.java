package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    @FXML
    public BorderPane window_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window_parent.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        Model.getInstance().getViewFactory().getSelectedView().addListener(((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case LOADING -> displayLoading();
                case WEATHER -> displayWeather();
                default -> displayWelcome();
            }
        }));
    }

    public void displayLoading() {
        window_parent.setCenter(Model.getInstance().getViewFactory().getLoadingView());
    }

    public void displayWeather() {
        window_parent.setCenter(Model.getInstance().getViewFactory().getForecastView());
    }

    public void displayWelcome() {
        window_parent.setCenter(Model.getInstance().getViewFactory().getWelcomeView());
    }


}
