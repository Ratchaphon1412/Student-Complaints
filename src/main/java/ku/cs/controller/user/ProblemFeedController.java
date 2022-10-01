package ku.cs.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ku.cs.State;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;

import java.util.Set;
import java.util.prefs.Preferences;

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
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        userName.setFont(font);
        content.setFont(font);
        title.setFont(font);
        status.setFont(font);
        this.report =report;
        userName.setText(report.getReporter().getUserName());
        content.setText(report.getCategory().getNameCategory());
        title.setText(report.getTitle());
        status.setText(report.getReportStage());

    }
}
