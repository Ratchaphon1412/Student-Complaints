package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.user.User;

import java.io.File;


public class AdminUserBanListController {


    @FXML
    private ImageView Img;

    @FXML
    private Label countBanLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label textLabel;
    private User user;

    public void setData(User user){
        this.user = user;
        nameLabel.setText(user.getUserName());
        dateLabel.setText(user.getDateBan());
        textLabel.setText(user.getRequestUnban());
        countBanLabel.setText(String.valueOf(user.getCountAccess()));
        String fs = File.separator;
        Image image = new Image(System.getProperty("user.dir") +fs+ "image" + fs +  "accounts" + fs+ user.getPathPicture());
        Img.setImage(image);


    }


}
