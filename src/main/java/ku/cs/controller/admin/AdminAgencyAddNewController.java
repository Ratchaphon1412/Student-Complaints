package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class AdminAgencyAddNewController {
    @FXML
    private TextField newAgency;

    @FXML
    private Button closeButton;
    private ProcessData processData;
    private AgencyLoad agencyLoad;


    @FXML
    private void initialize(){
        processData = new ProcessData<>();
        agencyLoad = (AgencyLoad) ApplicationController.getAgencyLoad();

    }
    @FXML
    private  void submitButton() throws IOException {
        String newAgencyText = newAgency.getText();
        System.out.println(newAgencyText);
        if(!newAgencyText.equals("")){
            //create agency
            LinkedHashMap<String,String> temp = new LinkedHashMap<>();
            temp.put("agency",newAgencyText);
            temp.put("stuffNameList","");
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
    private void close(){
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }

}
