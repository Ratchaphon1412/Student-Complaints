package ku.cs.controller.admin;

import animatefx.animation.FadeInUp;
import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.models.staff.StaffList;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.prefs.Preferences;

public class AdminAgencyAddNewController {
    @FXML
    private TextField newAgency;

    @FXML
    private Button closeButton;

    @FXML
    private ChoiceBox<String> choiceBoxAgency;
    @FXML
    private Label titleLabel;
    @FXML
    private Label changeNameLabel;
    @FXML
    private Label toLabel;
    @FXML
    private GridPane gridPaneAddAgency;
    @FXML
    private TextField changeNameAgencyTextField;

    private ProcessData processData;
    private AgencyLoad agencyLoad;


    @FXML
    private void initialize(){
        processData = new ProcessData<>();
        agencyLoad = (AgencyLoad) ApplicationController.getAgencyLoad();
        StaffList staffList = new StaffList(processData.getDataBase().getAccountList(),processData.getDataBase().getAgencyList());
        choiceBoxAgency.getItems().addAll(staffList.getAgency());
        //set Font
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        titleLabel.setFont(font);
        toLabel.setFont(font);
        changeNameLabel.setFont(font);
        //set Animation
        new FadeInUp(gridPaneAddAgency).play();
//        new ZoomIn(gridPaneAddAgency).play();

    }
    @FXML
    private  void submitButtonAddNewAgency(ActionEvent actionEvent) throws IOException {
        String newAgencyText = newAgency.getText();
        System.out.println(newAgencyText);
        if(!newAgencyText.equals("")){
            //create agency
            LinkedHashMap<String,String> temp = new LinkedHashMap<>();
            temp.put("agency",newAgencyText);
            temp.put("staffNameList","");
            List<LinkedHashMap<String,String>> agencyList = processData.getDataBase().getAgencyList();
            System.out.println(agencyList);
            agencyList.add(temp);
            System.out.println(agencyList);
            DataBase dataBase = processData.getDataBase();
            dataBase.setAgencyList(agencyList);
            dataBase.saveToDatabase();
            //reload
            agencyLoad.reloadAgency();
            close();
        }


    }

    @FXML
    private void changeNameAgency(ActionEvent actionEvent) throws IOException {
        String nameAgencyText = changeNameAgencyTextField.getText();
        if(! (nameAgencyText.equals("") && choiceBoxAgency == null)){
            LinkedHashMap<String,String> temp = new LinkedHashMap<>();
            List<LinkedHashMap<String,String>> agencyList = processData.getDataBase().getAgencyList();
            for(LinkedHashMap<String,String> checkAgency : agencyList){
                if (checkAgency.get("agency").equals(choiceBoxAgency.getValue())){
                    String oldNameAgency = checkAgency.get("agency");
                    String newNameAgency = nameAgencyText;
                    checkAgency.replace("agency",oldNameAgency,newNameAgency);
                    System.out.println(checkAgency);
                }
            }
            DataBase dataBase = processData.getDataBase();
            dataBase.setAgencyList(agencyList);
            dataBase.saveToDatabase();
            agencyLoad.reloadAgency();
            close();

        }
    }

    @FXML
    private void close(){
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }


}
