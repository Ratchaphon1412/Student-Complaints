package ku.cs.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import ku.cs.ApplicationController;

import java.io.IOException;

public class AddCategoryController {
    @FXML
    void closeButton(ActionEvent event) {
        try {
            ApplicationController.goTo("AdminCategory");
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
