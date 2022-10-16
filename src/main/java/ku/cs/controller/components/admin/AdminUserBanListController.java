package ku.cs.controller.components.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.State;
import ku.cs.controller.admin.BanAndUnBan;
import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;


public class AdminUserBanListController {
    @FXML
    private Circle Img;

    @FXML
    private Label countBanLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label textLabel;
    private User user;
    private BanAndUnBan banAndUnBan;
    private Admin admin;
    private ProcessData processData;
    @FXML
    public void initialize() throws IOException {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        //set font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        nameLabel.setFont(font);
        dateLabel.setFont(font);
        textLabel.setFont(font);
        countBanLabel.setFont(font);
    }

    @FXML
    private void click(ActionEvent actionEvent) throws IOException {
        admin.banUser(user);
        processData.changeData(user,"banUser");
        banAndUnBan.onClickBanOrUnban();
    }

    public void setData(User user,Admin admin,BanAndUnBan banAndUnBan){
        this.admin = admin;
        this.user = user;
        this.banAndUnBan = banAndUnBan;
        this.processData = new ProcessData<User>();
        nameLabel.setText(user.getUserName());
        dateLabel.setText(user.getDateBan());
        textLabel.setText(user.getRequestUnban());
        countBanLabel.setText(String.valueOf(user.getCountAccess()));
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ user.getPathPicture());
        Img.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));
    }


}
