package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedHashMap;

public class LogAccontController {

    @FXML
    private Circle picture;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;

    private LinkedHashMap<String,String> account;

    public void setData(LinkedHashMap<String,String> account){


           nameLabel.setText(account.get("userName"));
           roleLabel.setText(account.get("role"));
           timeLabel.setText(account.get("time"));
           dateLabel.setText(account.get("date"));
           File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.get("pathPicture"));
           picture.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));


    }

}
