package ku.cs.controller.components.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.State;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.prefs.Preferences;

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

    @FXML
    public void initialize() throws IOException {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        //set font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        nameLabel.setFont(font);
        roleLabel.setFont(font);
        timeLabel.setFont(font);
        dateLabel.setFont(font);
    }

    public void setData(LinkedHashMap<String,String> account){

        nameLabel.setText(account.get("userName"));
        roleLabel.setText(account.get("role"));
        timeLabel.setText(account.get("time"));
        dateLabel.setText(account.get("date"));
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.get("pathPicture"));
        Image image = new Image(String.valueOf(desDir.toURI().toString()),500, 0 ,true,true);
        if(!image.isError()){
            picture.setFill(new ImagePattern(image));
        }



    }

}
