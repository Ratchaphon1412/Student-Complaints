package ku.cs.controller.components.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controller.admin.BanAndUnBan;
import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;

public class BanUserReportController {


    @FXML
    private Circle Img;
    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label headData;
    @FXML
    private Button clickId;

    private BanAndUnBan banAndUnBan;

    private User user;
    private Admin admin;
    private ProcessData processData;


    @FXML
    private void click(ActionEvent actionEvent) throws IOException {
        admin.banUser(user);
        processData.changeData(user,"banUser");
        clickId.setOnAction(null);
        banAndUnBan.onClickBanOrUnban();

    }


    public void setData(User user, Admin admin,BanAndUnBan banAndUnBan){
        this.user = user;
        this.admin = admin;
        this.banAndUnBan = banAndUnBan;
        this.processData = new ProcessData<User>();
        nameLabel.setText(user.getUserName());
//        if(user.get)

        timeLabel.setText(user.getTime());
        dateLabel.setText(user.getDateRequestBan());
        titleLabel.setText(user.getCategory());
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ user.getPathPicture());
        Img.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));
    }




}
