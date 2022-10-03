package ku.cs.controller.staff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.navbar.NavbarStaffController;
import ku.cs.controller.components.staff.StaffFeedController;
import ku.cs.models.report.Report;
import ku.cs.models.staff.Staff;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

public class StaffController {
    @FXML
    private ChoiceBox<String> StatusChoiceBox;

    @FXML
    private Label roleLabel;

    @FXML
    private Circle imageStaff;

    @FXML
    private GridPane minisetting;

    @FXML
    private Label nameProblem;

    @FXML
    private Label nameStaffLabel;

    @FXML
    private TextArea processTextArea;

    @FXML
    private GridPane root;

    @FXML
    private ScrollPane showDataProblemScrollPane;
    private ProcessData processData;
    private Staff account;
    private FXMLLoader fxmlLoader;
    private SwitchTheme changeTheme;
    private Report reportProcess;
    private List<Report> reportList;


    @FXML
    public void initialize() throws IOException {

        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        root.getStylesheets().add(getClass().getResource(style).toExternalForm());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        roleLabel.setFont(font);
        nameStaffLabel.setFont(font);
        nameProblem.setFont(font);


        account = (Staff)ApplicationController.getData();

        nameStaffLabel.setText(account.getUserName());
        roleLabel.setText(account.getRole());
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ account.getPathPicture());
        imageStaff.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));
        imageStaff.setStroke(Color.TRANSPARENT);
        changeTheme = new SwitchTheme() {
            @Override
            public void changeTheme(String theme) throws IOException {
                //save theme
                State state = new State();
                state.setTempData();
                state.saveThemeToConfig(theme);
                //change state
                Preferences preferences = Preferences.userRoot().node(State.class.getName());
                preferences.put("theme",theme);
                //change stylesheet in main page
                root.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
                root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
                root.getStylesheets().add(getClass().getResource(style).toExternalForm());
            }
        };


        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = (GridPane)fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);

        //navbar
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staff/navBarStaff.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarStaffController navbarStaffController = fxmlLoader.getController();
        navbarStaffController.setStaff(account);
        root.add(navbar,0,0);


        //refetch components
        refetch();

    }

    private void refetch() throws IOException {


        processData = new ProcessData<>();
        StatusChoiceBox.getItems().addAll("in progress","finich");

        reportList = processData.getReportList().getReportListAgency(account.getAgency());
        ShowProcessProblem showProcessProblem = new ShowProcessProblem() {
            @Override
            public void showData(Report report) {
                setShowProcess(report);
            }
        };

        GridPane gridPanePosts = new GridPane();
        showDataProblemScrollPane.setContent(gridPanePosts);

        int i = 0;
        for(Report temp : reportList){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staff/staffFeed.fxml"));
            GridPane posts = (GridPane) fxmlLoader.load();

            StaffFeedController staffFeedController = fxmlLoader.getController();
            staffFeedController.setData(temp,showProcessProblem);

            gridPanePosts.add(posts, 0, i++);
            GridPane.setMargin(posts, new Insets(0,0,5,15));
        }

    }


    private void setShowProcess(Report report){
        this.reportProcess = report;
        processTextArea.setText("");
        nameProblem.setText("");
        StatusChoiceBox.getItems().clear();
        StatusChoiceBox.getItems().addAll("in progress","finich");
        if(report.getReportStage().equals("finich")){
            processTextArea.setText(report.getProcess());
            processTextArea.setDisable(true);
            nameProblem.setText(report.getTitle());
        }
        else{
            processTextArea.setDisable(false);
            
            
            nameProblem.setText(report.getTitle());
        }
    }


    public void saveProcessStaff(ActionEvent actionEvent) throws IOException {
        String reportStage  =  StatusChoiceBox.getValue();
        String processText = processTextArea.getText();
        String process = null;
        String[] processTextSplit = processText.split("\n");
        for(String temp : processTextSplit){
            if(processTextSplit[0].equals(temp)){
                process = temp;
            }
            else{
                process+=temp;
            }
        }
        if(process != null && reportStage != null && reportProcess != null && !(reportProcess.getReportStage().equals("finich"))){
            reportProcess.setProcessProblem(process,reportStage,account.getUserName());
            processData.changeData(reportProcess,"addPrecessProblem");
            this.nameProblem.setText("");
            this.processTextArea.setText("");
            StatusChoiceBox.getItems().clear();
            refetch();
        }
        else{
            System.out.println("ใส่ข้อมูลก่อน");
        }
        System.out.println(process+ " " + reportStage);
    }

    @FXML
    public void handleStaffSetting(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("SettingStaff",account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
