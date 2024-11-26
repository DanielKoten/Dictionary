module tubes.tubesstrukdat {
    requires javafx.controls;
    requires javafx.fxml;


    opens tubes.tubesstrukdat to javafx.fxml;
    exports tubes.tubesstrukdat;
    exports tubes.tubesstrukdat.Controller;
    opens tubes.tubesstrukdat.Controller to javafx.fxml;
}