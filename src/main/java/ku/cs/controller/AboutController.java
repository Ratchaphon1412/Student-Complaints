package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ku.cs.Application;
import ku.cs.ApplicationController;

import javax.swing.text.Element;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController {
    @FXML
    private GridPane root;

    @FXML
    private void initialize(){
        Image image = new Image(getClass().getResource("/ku/cs/assets/images/About.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(900);
        imageView.setFitHeight(450);
        root.add(imageView,1,1);



    }



    @FXML
    public void closeWindows(ActionEvent event) throws IOException {
        ApplicationController.goTo("Login");
    }


}
