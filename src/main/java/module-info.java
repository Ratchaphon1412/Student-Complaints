module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.dataformat.csv;
    requires com.fasterxml.jackson.databind;

    opens ku.cs to javafx.fxml;
    exports ku.cs;
    opens ku.cs.controller to javafx.fxml;
    exports ku.cs.controller;

}