package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.ApplicationController;

import java.io.IOException;

public class LoginController {
    @FXML
    public void  handleChangePasswordButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goToNew("changePassword");
        } catch (IOException e) {
            System.err.println(e);


        }


    }
}
