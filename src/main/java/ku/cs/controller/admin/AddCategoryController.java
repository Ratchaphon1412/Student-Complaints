package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ku.cs.ApplicationController;


import ku.cs.State;
import ku.cs.models.admin.Admin;

import ku.cs.service.DataBase;

import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.ArrayList;

public class AddCategoryController {

    @FXML
    private TextField addCatagoryField;

    private ProcessData<Admin> processData;

    @FXML
    private TextField addText;

    @FXML
    private ChoiceBox<String> dropDownType;
    @FXML
    private ChoiceBox<String> dropDownAgency;

    @FXML
    private Button close;

    @FXML
    private Label text;
    private ArrayList<String> listExampleString;
    private ArrayList<String> textString;
    private  ArrayList<String> imageString;
    private ArrayList<String> checkType;
    private ArrayList<String> dropDownAgencyList;
    DataBase dataBase;


    public void initialize() throws IOException {

        dataBase = new DataBase();
        listExampleString = new ArrayList<>();
        processData = new ProcessData();
        textString = new ArrayList<>();
        imageString = new ArrayList<>();
        checkType = new ArrayList<>();
        dropDownAgencyList = new ArrayList<>();
        initializeCategory();




        //initializeCategory();

    }


    @FXML
    void initializeCategory() throws IOException {
//        fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/categoryList.fxml"));
//        AnchorPane categoryComponant = (AnchorPane) fxmlLoader.load();
//        AddCategoryController addCategoryController = fxmlLoader.getController();
//
//        GridPane.setMargin(categoryComponant, new Insets(0,0,5,0));

        String[] pattern = {"text", "image"};
        dropDownType.getItems().addAll(pattern);
        dropDownAgencyList = processData.dropDownAgency();
        dropDownAgency.getItems().addAll(dropDownAgencyList);
    }

    @FXML
    void closeButton() {
        Stage state = (Stage)close.getScene().getWindow();
        state.close();
    }



    @FXML
    private void handleExample() {
        String type = dropDownType.getValue();
        int count = 1;
        listExampleString.add(addText.getText());
        String label = "";
        if(type.equals("text")){
            textString.add(addText.getText());
            checkType.add(" text");
            for (String temp : listExampleString) {
                label += count + "." + temp +  checkType.get(count-1) +"\n";
                count++;
            }
            text.setWrapText(true);
            text.setText(label);
            addText.clear();


        } else if (type.equals("image")) {
            imageString.add(addText.getText());
            checkType.add(" image");
            for (String temp : listExampleString) {
                label += count + "." + temp + checkType.get(count-1) + "\n";
                count++;
            }
            text.setWrapText(true);
            text.setText(label);
            addText.clear();

        }
    }

    public void handleSubmitButton() throws IOException {

        String category = addCatagoryField.getText();
        processData.addCategory(category);

        for (String dataLine : textString) {
            processData = new ProcessData<>();
            processData.addText(category, dataLine);
        }

        for (String dataLine : imageString){
            processData = new ProcessData<>();
            processData.addImage(category,dataLine);
        }
        processData.selectAgency(category, dropDownAgency.getValue());
        clear();

    }
    public void clear(){
        text.setText("");
        //addCatagoryField.clear();
        listExampleString.clear();
        textString.clear();
        imageString.clear();
        checkType.clear();
    }



}
