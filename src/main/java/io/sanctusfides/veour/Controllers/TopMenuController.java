package io.sanctusfides.veour.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.sanctusfides.veour.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
//          First use the geo-fetching API to convert city,state names into lat and long in the API Driver
            try {
                Model.getInstance().getApiDriver().setCityLatAndLong(search_fld.getText());
            } catch (JsonProcessingException | ParseException e) {
                throw new RuntimeException(e);
            }
//          API Driver will now use the lat and long set above to fetch the weather for that weather.
            try {
                System.out.println(Arrays.toString(Model.getInstance().getApiDriver().getWeather()));
                Model.getInstance().getApiDriver().getWeather();
                System.out.println(Model.getInstance().getApiDriver().getWeather());
//                showForecast();
            } catch (ParseException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
