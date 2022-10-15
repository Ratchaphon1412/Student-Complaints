package ku.cs;


import animatefx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Preloader implements Initializable {
    @FXML
    private GridPane rootGridPane;

    @FXML
    private Label titleStudent;
    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;



    @FXML
    private Label loadingLable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),12);
        titleStudent.setFont(font);
        loadingLable.setFont(font);
            new ZoomIn(rootGridPane).play();
            new SplashScreen().start();
    }

    class SplashScreen extends Thread{

        @Override
        public void run(){
//                new Shake(rootGridPane).setDelay(Duration.valueOf("1000ms")).play();

            try {
                new Bounce(circle1).setCycleCount(14).setDelay(Duration.valueOf("500ms")).play();
                new Bounce(circle2).setCycleCount(14).setDelay(Duration.valueOf("1000ms")).play();
                new Bounce(circle3).setCycleCount(14).setDelay(Duration.valueOf("1100ms")).play();
                Thread.sleep(3000);
                new ZoomOut(rootGridPane).setDelay(Duration.valueOf("1100ms")).play();
                Thread.sleep(2000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                           try {
                               ApplicationController.goTo("Login");
//                            rootGridPane.getScene().getWindow().hide();
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           }
                    }
                });

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }



    }
}