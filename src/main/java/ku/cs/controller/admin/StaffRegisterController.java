package ku.cs.controller.admin;

import animatefx.animation.FadeInUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.models.staff.Staff;
import ku.cs.models.staff.StaffList;
import ku.cs.service.DynamicDatabase;
import ku.cs.service.ProcessData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class StaffRegisterController {
    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passWord;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label singleFile;
    @FXML
    private Label uploadPictureLabel;
    @FXML
    private Label sighUpLabel;
    @FXML
    private ImageView userImage;

    @FXML
    private ChoiceBox choiceAgency;
    @FXML
    private GridPane girdPaneSighup;
    @FXML
    private Button close;

    private StaffList staffList;

    private ProcessData dataBase;
    private AgencyLoad agencyLoad;
    private String path;
    private File file;



    @FXML
    private void initialize(){
        ProcessData processData = new ProcessData<>();
        agencyLoad = (AgencyLoad) ApplicationController.getAgencyLoad();
        staffList = new StaffList(processData.getDataBase().getAccountList(),processData.getDataBase().getAgencyList());
        choiceAgency.getItems().addAll(staffList.getAgency());
        //set Font
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        sighUpLabel.setFont(font);
        singleFile.setFont(font);
        uploadPictureLabel.setFont(font);
        //set Animation
        new FadeInUp(girdPaneSighup).play();
    }


    @FXML
    private void  signUpButton() throws IOException {
        String user = userName.getText();
        String emails = email.getText();
        String password = passWord.getText();
        String confirmpassword = confirmPassword.getText();
        dataBase = new ProcessData<>();
        if(user != "" && emails != "" && password != "" && confirmpassword != "") {

            String selectAgency = "";
            if (!dataBase.checkAccount(emails)) {
                if (password.equals(confirmpassword)) {

                    if (choiceAgency.getValue() != null) {
                        selectAgency = choiceAgency.getValue().toString();
                    }
                    Staff newUser = new Staff(emails, user, password, path, "staff", selectAgency);
                    DynamicDatabase<Staff> database = new ProcessData<>();
                    boolean checkregister = database.registerAccount(newUser, file, "staff");
                    if (checkregister) {
//                        ApplicationController.goTo("Login");
                        agencyLoad.reloadAgency();
                        closeWindows();
                    } else {
                        ApplicationController.goToNew("Alert", "Failed to register");
                    }

                } else {
                    ApplicationController.goToNew("Alert", "password not correct");
                    System.out.println("passWord not correct");
                }
            } else {
                ApplicationController.goToNew("Alert", "Have Account in Database");
                System.out.println("Have Account in Database");
            }
        }else{
            ApplicationController.goToNew("Alert", "Please complete the text.");
        }


    }

    @FXML
    public void fileChooser(ActionEvent actionEvent) {
        FileChooser choosefile = new FileChooser();
        List<String> listfile = new ArrayList<>();
        listfile.add("*.jpg");
        listfile.add("*.png");
        listfile.add("*jpeg");
        choosefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Picture", listfile));


        file = choosefile.showOpenDialog(null);


        if (file != null) {
            singleFile.setText("Selected File: " + file.getAbsolutePath());
            path = file.getAbsolutePath();
            userImage.setImage(new Image(new File(path).toURI().toString()));
        }

    }

    @FXML
    private void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }



}
