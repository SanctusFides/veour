package io.sanctusfides.veour.Views;

import io.sanctusfides.veour.Controllers.ForecastCellController;
import io.sanctusfides.veour.Models.Forecast;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ForecastCellFactory extends ListCell<Forecast> {

    public ForecastCellFactory() {

    }

    @Override
    protected void updateItem(Forecast forecast, boolean empty) {
        super.updateItem(forecast, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ForecastCell.fxml"));
            ForecastCellController controller = new ForecastCellController(forecast);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
