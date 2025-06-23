module io.sanctusfides.veour {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens io.sanctusfides.veour to javafx.fxml;
    exports io.sanctusfides.veour;
    exports io.sanctusfides.veour.Controles;
    opens io.sanctusfides.veour.Controles to javafx.fxml;
}