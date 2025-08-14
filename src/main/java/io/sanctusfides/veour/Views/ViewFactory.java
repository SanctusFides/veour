package io.sanctusfides.veour.Views;

import io.sanctusfides.veour.Controllers.WindowController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewFactory {

    private final ObjectProperty<ViewOptions> selectedView;

    @FXML
    private HBox topMenu;
    @FXML
    private HBox welcomeView;
    @FXML
    private HBox forecastView;
    @FXML
    private HBox loadingView;


    public ViewFactory() {
        this.selectedView = new SimpleObjectProperty<>();
    }


    // Main window container for the app that opens on launch and houses the center and top panels
    public void showWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Window.fxml"));
        WindowController controller = new WindowController();
        loader.setController(controller);
        createStage(loader);
    }

    public HBox getWelcomeView() {
        if (welcomeView == null) {
            try {
                welcomeView = new FXMLLoader(getClass().getResource("/Fxml/WelcomePanel.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return welcomeView;
    }

    public HBox getLoadingView() {
        if (loadingView == null) {
            try {
                loadingView = new FXMLLoader(getClass().getResource("/Fxml/LoadingPanel.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return loadingView;
    }

    public HBox getForecastView() {
        try {
            forecastView = new FXMLLoader(getClass().getResource("/Fxml/WeatherPanel.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecastView;
    }

    // reusable method to create the stage, used in the Show section
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setTitle("Veour Forecast");
        stage.setResizable(false);
        stage.show();
    }

    public ObjectProperty<ViewOptions> getSelectedView() {
        return selectedView;
    }
}
