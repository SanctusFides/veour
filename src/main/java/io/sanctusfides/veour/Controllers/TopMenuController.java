package io.sanctusfides.veour.Controllers;

import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Utilities.Utility;
import io.sanctusfides.veour.Views.ViewOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

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
        Utility loader = new Utility();
        loader.loadCityList();
        TextFields.bindAutoCompletion(search_fld,Model.getInstance().getCities());
    }


    public void loadWeather() throws Exception {
//          Display the loading screen
        Model.getInstance().getViewFactory().getSelectedView().set(ViewOptions.LOADING);
//          Uses the user search results to look up lat&long to use for fetching their weather then displays forecast
        Model.getInstance().getApiDriver().loadWeather(search_fld.getText());
    }
}
