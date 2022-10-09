package ku.cs.controller.components.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.prefs.Preferences;

public class ReportUserOrPost {


    @FXML
    private Button buttonSubmit;

    @FXML
    private Label subtitle;

    @FXML
    private Label title;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button close;

    @FXML
    private TextArea textArea;

    private Report report;
    private ProcessData processDataReport;



    @FXML
    private void initialize() throws IOException {
        report = (Report) ApplicationController.getData();
        this.processDataReport = new ProcessData<Report>();
        choiceBox.getItems().addAll("User","Post");
    }


    public void clickSubmit(ActionEvent actionEvent) throws IOException {
        if(choiceBox.getValue().equals("User")){
            String reportText = textArea.getText();
            report.setReportPostText(reportText.replace('\n', ' '));
            processDataReport.changeData(report,"reportUser");
            closeButton();
        }
        else if (choiceBox.getValue().equals("Post")) {
            String reportText = textArea.getText();
            report.setReportPostText(reportText.replace('\n', ' '));
            processDataReport.changeData(report,"reportPost");
            closeButton();
        }
    }

    @FXML
    private void closeButton(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
}
