package ku.cs.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import ku.cs.ApplicationController;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.IOException;

public class AddCategoryController {
    @FXML
    private TextField addCatagoryField;
    private ProcessData processData;

    @FXML
    void closeButton(ActionEvent event) {
        try {
            ApplicationController.goTo("AdminCategory");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    void applyButton(ActionEvent event) throws IOException {
        addCatagoryField.getText();
        System.out.println("get category");
    }


}
