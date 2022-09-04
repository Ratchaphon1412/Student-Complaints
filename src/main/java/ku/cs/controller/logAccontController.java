package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedHashMap;

public class logAccontController {

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    private LinkedHashMap<String,String> account;

    public void setData(LinkedHashMap<String,String> account){
        this.account = account;
        this.nameLabel.setText(account.get("userName"));
        this.roleLabel.setText(account.get("role"));
        Image image = new Image(getClass().getResourceAsStream(account.get("")));
    }

}
