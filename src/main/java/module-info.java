module io.sanctusfides.veour {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.net.http;
    requires jdk.jsobject;
    requires json.simple;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    opens io.sanctusfides.veour to javafx.fxml;
    exports io.sanctusfides.veour;
    exports io.sanctusfides.veour.Controllers;
    opens io.sanctusfides.veour.Controllers to javafx.fxml;
}