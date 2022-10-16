package ku.cs.controller.components.staff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.staff.ShowProcessProblem;
import ku.cs.models.report.Report;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.prefs.Preferences;

public class StaffFeedController {

    @FXML
    private Label content;

    @FXML
    private Label nameAgency;

    @FXML
    private Label nameStaff;

    @FXML
    private Label status;

    @FXML
    private Label title;
    @FXML
    private Label textAgency;
    @FXML
    private Label textTitle;
    @FXML
    private Label textContent;
    @FXML
    private Label textStatus;
    @FXML
    private Label textStaff;
    private Report report;
    private ShowProcessProblem showProcessProblem;


    public void setData(Report report, ShowProcessProblem showProcessProblem){
        this.showProcessProblem = showProcessProblem;
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        nameAgency.setFont(font);
        nameAgency.setWrapText(true);
        nameStaff.setFont(font);
        nameStaff.setWrapText(true);
        content.setFont(font);
        content.setWrapText(true);
        title.setFont(font);
        title.setWrapText(true);
        status.setFont(font);
        status.setWrapText(true);
        textAgency.setFont(font);
        textAgency.setWrapText(true);
        textStatus.setFont(font);
        textStatus.setWrapText(true);
        textContent.setFont(font);
        textContent.setWrapText(true);
        textTitle.setFont(font);
        textTitle.setWrapText(true);
        textStaff.setFont(font);
        textStaff.setWrapText(true);
        this.report =report;
        nameAgency.setText(report.getAgency());
        nameStaff.setText(report.getStaff());
        status.setText(report.getReportStage());
//        LinkedHashMap<String,List<LinkedHashMap<String,String>>> temp = report.getCategory().getMapDataPattern();
        LinkedHashMap<String, LinkedHashMap<String, String>> temp = report.getCategory().getMapDataPattern();
        LinkedHashMap<String,String> temp2= temp.get("text");
        String[] keyContent = temp2.keySet().toArray(String[]::new);


        content.setText(temp2.get(keyContent[0]));
        title.setText(report.getTitle());
        status.setText(report.getReportStage());

    }
    @FXML
    public void  viewPost(ActionEvent actionEvent) throws IOException {
        System.out.println("check");
        ApplicationController.goToNew("DetailReport",report);
    }

    @FXML
    public void showProcess(ActionEvent actionEvent){
        showProcessProblem.showData(report);
    }


}
