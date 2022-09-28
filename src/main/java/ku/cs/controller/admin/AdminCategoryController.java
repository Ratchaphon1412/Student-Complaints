package ku.cs.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.ApplicationController;

import java.io.IOException;

public class AdminCategoryController {
    @FXML
    void goToAddCategoryButton(ActionEvent event) throws IOException {
        ApplicationController.goTo("AddCategory");
    }
}
