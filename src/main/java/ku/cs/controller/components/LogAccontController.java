package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
        this.account = account;
        nameLabel.setText(account.get("userName"));
        roleLabel.setText(account.get("role"));
        timeLabel.setText(account.get("time"));
        dateLabel.setText(account.get("date"));
        picture.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/image/accounts/"+account.get("pathPicture")))));
//        Image image = new Image(getClass().getResourceAsStream("2022-08-11-java-logo-vert-blk.png"));
//        img.setImage(image);
    }

}
