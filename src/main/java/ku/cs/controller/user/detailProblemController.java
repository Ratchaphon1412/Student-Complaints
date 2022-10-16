package ku.cs.controller.user;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.components.user.ReportImage;
import ku.cs.controller.components.user.ReportText;
import ku.cs.models.report.Report;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.prefs.Preferences;

public class detailProblemController {
    @FXML
    private Label titleReport;

    @FXML
    private  Label status;

    @FXML
    private Label date;


    @FXML
    private Label titleDetailProblem;
    @FXML
    private Label titleStatus;

    @FXML
    private  Label titleAgency;

    @FXML
    private Label titleDate;

    @FXML
    private ScrollPane scroll;
    @FXML
    private Label agency;
    @FXML
    private Button close;
    @FXML
    private GridPane root;
    @FXML
    private TextArea detailStaffProcesss;
    @FXML
    private Label datailStaffTextLabel;


    private Report report;


    @FXML
    private void initialize() throws IOException {

        report = (Report) ApplicationController.getData();
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        titleReport.setText(report.getTitle());
        agency.setText(report.getAgency());
        date.setText(report.getDate().toString());
        status.setText(report.getReportStage());
        detailStaffProcesss.setText(report.getProcess());

        agency.setFont(font);
        titleReport.setFont(font);
        titleReport.setWrapText(true);
        status.setFont(font);
        date.setFont(font);
        titleDetailProblem.setFont(font);
        titleStatus.setFont(font);
        titleAgency.setFont(font);
        titleDate.setFont(font);
        detailStaffProcesss.setFont(font);
        datailStaffTextLabel.setFont(font);

        new FadeInUp(root).play();

        GridPane tempGrid = new GridPane();

        LinkedHashMap<String, LinkedHashMap<String, String>> dataReportMap = report.getCategory().getMapDataPattern();
        int i = 0;
        for(String key:dataReportMap.get("text").keySet()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/reportText.fxml"));
            GridPane temp = (GridPane) fxmlLoader.load();
            ReportText reportText = fxmlLoader.getController();
            reportText.setDataText(key,dataReportMap.get("text").get(key));
            tempGrid.add(temp,0,i);
            i++;
        }

        for(String key:dataReportMap.get("image").keySet()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/reportImage.fxml"));
            GridPane temp = (GridPane) fxmlLoader.load();ReportImage reportImage = fxmlLoader.getController();
            reportImage.setDataImage(key,dataReportMap.get("image").get(key));
            tempGrid.add(temp,0,i);
            i++;
        }
        scroll.setContent(tempGrid);

    }

    @FXML
    private void closeButton(ActionEvent actionEvent){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }






}
