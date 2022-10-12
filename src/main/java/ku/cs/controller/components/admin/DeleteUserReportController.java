package ku.cs.controller.components.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.admin.BanAndUnBan;
import ku.cs.models.admin.Admin;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class DeleteUserReportController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Button clickIdDelete;
    @FXML
    private Button clickIdPass;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label textSubjectLabel;
    @FXML
    private Label textDateLabel;
    @FXML
    private Label headData;
    private BanAndUnBan banAndUnBan;

    private Report report;
    private Admin admin;
    private ProcessData processData;

    @FXML
    public void initialize() throws IOException{
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        subjectLabel.setFont(font);
        dateLabel.setFont(font);
        headData.setFont(font);
        textDateLabel.setFont(font);
        titleLabel.setFont(font);
        textSubjectLabel.setFont(font);
    }
    @FXML
    private void clickPost(ActionEvent actionEvent) throws IOException {
        processData.changeData(report,"deletePost");
        clickIdDelete.setOnAction(null);
        banAndUnBan.onClickBanOrUnban();

    }
    @FXML
    private void clickDeleteReportPost(ActionEvent actionEvent) throws IOException {
        processData.changeData(report,"deleteReport");
        clickIdPass.setOnAction(null);
        banAndUnBan.onClickBanOrUnban();
    }


    public void setData(Report report, Admin admin, BanAndUnBan banAndUnBan){
        this.report = report;
        this.admin = admin;
        this.banAndUnBan = banAndUnBan;
        this.processData = new ProcessData<User>();
        subjectLabel.setText(report.getTextReport());
        titleLabel.setText(report.getTitle());
        dateLabel.setText(report.getDateTimeRequestDalete());
    }
    @FXML
    public void  viewPost(ActionEvent actionEvent) throws IOException {
        ApplicationController.goToNew("DetailReport",report);
    }

}
