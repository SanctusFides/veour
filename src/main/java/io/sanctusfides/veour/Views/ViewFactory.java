package io.sanctusfides.veour.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewFactory {

    private HBox weatherPanel;

/*
    SHOW SECTION
    this section is for methods that push the created views to user
*/
    // Main window container for the app that opens on launch and houses the center and top panels
    public void showWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Window.fxml"));
//        WindowController controller = new WindowController();
//        loader.setController(controller);
        createStage(loader);
    }

/*
    CREATION SECTION
    this section is for methods that create Views and View elements
*/


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
/*
    GENERAL UTILITY SECTION
*/
    public void closeStage(Stage stage) {
        stage.close();
    }
}
