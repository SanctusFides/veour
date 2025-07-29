package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Views.ViewOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TopMenuController implements Initializable {

    @FXML
    public Label search_lbl;
    @FXML
    public TextField search_fld;
    @FXML
    public Button search_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void loadWeather() {
        Model.getInstance().getViewFactory().getSelectedView().set(ViewOptions.LOADING);
        try {
//              First use the geo-fetching API to convert city,state names into lat and long in the API Driver
            Model.getInstance().getApiDriver().setCityLatAndLong(search_fld.getText());
//              API Driver will now use the lat and long set above to fetch the weather for that weather.
            Model.getInstance().getApiDriver().getWeather();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Model.getInstance().getViewFactory().getSelectedView().set(ViewOptions.WEATHER);
        }
    }
}
