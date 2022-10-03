module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.dataformat.csv;
    requires com.fasterxml.jackson.databind;
    requires java.prefs;
    opens ku.cs to javafx.fxml;
    exports ku.cs;
    opens ku.cs.controller to javafx.fxml;
    exports ku.cs.controller;
    exports ku.cs.controller.components;
    opens ku.cs.controller.components to javafx.fxml;
    exports ku.cs.controller.admin;
    opens ku.cs.controller.admin to javafx.fxml;
    exports ku.cs.controller.login;
    opens ku.cs.controller.login to javafx.fxml;
    exports ku.cs.controller.user;
    opens ku.cs.controller.user to javafx.fxml;
    exports ku.cs.controller.staff;
    opens ku.cs.controller.staff to javafx.fxml;
    exports ku.cs.controller.components.navbar;
    opens ku.cs.controller.components.navbar to javafx.fxml;
    exports ku.cs.controller.components.staff;
    opens ku.cs.controller.components.staff to javafx.fxml;

}