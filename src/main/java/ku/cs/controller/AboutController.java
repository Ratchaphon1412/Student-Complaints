package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.Application;
import ku.cs.ApplicationController;

import java.io.IOException;

public class AboutController {

    @FXML
    public void closeWindows(ActionEvent event) throws IOException {
        ApplicationController.goTo("Login");
    }
}
