module com.phe.pmzviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.phe.pmzviewer to javafx.fxml;
    exports com.phe.pmzviewer;
    exports com.phe.pmzviewer.controller;
    opens com.phe.pmzviewer.controller to javafx.fxml;
}