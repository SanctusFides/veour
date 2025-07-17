package io.sanctusfides.veour.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.sanctusfides.veour.Models.Model;
import io.sanctusfides.veour.Views.ViewOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TopMenuController implements Initializable {


    public Label search_lbl;
    public TextField search_fld;
    public Button search_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        search_btn.setOnAction(actionEvent -> {
            try {
                Model.getInstance().getViewFactory().getSelectedView().set(ViewOptions.LOADING);
//              First use the geo-fetching API to convert city,state names into lat and long in the API Driver
                Model.getInstance().getApiDriver().setCityLatAndLong(search_fld.getText());
//              API Driver will now use the lat and long set above to fetch the weather for that weather.
                Model.getInstance().getApiDriver().getWeather();
                System.out.println(Arrays.toString(Model.getInstance().getApiDriver().getWeather()));
            } catch (JsonProcessingException | ParseException e) {
                e.printStackTrace();
            } finally {
                Model.getInstance().getViewFactory().getSelectedView().set(ViewOptions.WEATHER);
            }
        });
    }
}
