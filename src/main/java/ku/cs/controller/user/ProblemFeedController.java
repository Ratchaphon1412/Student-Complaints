package ku.cs.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;

import java.util.Set;

public class ProblemFeedController {

    @FXML
    private Label userName;

    @FXML
    private Label title;

    @FXML
    private Label content;

    @FXML
    private Label status;

    private Report report;



    public void setReport(Report report){
        this.report =report;
        userName.setText(report.getReporter().getUserName());
        content.setText(report.getCategory().getNameCategory());
        title.setText(report.getTitle());
        status.setText(report.getReportStage());

    }
}
