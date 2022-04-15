module com.phe.pmzviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.phe.pmzviewer to javafx.fxml;
    exports com.phe.pmzviewer;
}