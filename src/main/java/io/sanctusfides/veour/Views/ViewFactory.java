package io.sanctusfides.veour.Views;

import io.sanctusfides.veour.Controllers.WindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewFactory {


    public ViewFactory() {


    }
    // Main window container for the app that opens on launch and houses the center and top panels
    public void showWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Window.fxml"));
        WindowController controller = new WindowController();
        loader.setController(controller);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
//            TODO Exception handling
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setTitle("Veour Forecast");
        stage.setResizable(false);
        stage.show();
    }
}
