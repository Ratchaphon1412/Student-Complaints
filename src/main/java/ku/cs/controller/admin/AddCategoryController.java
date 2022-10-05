package ku.cs.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.ApplicationController;
import ku.cs.controller.components.StaffListAgencyController;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AddCategoryController {

    @FXML
    private GridPane girdPaneAddCategory;
    private ProcessData processData;
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField addCatagoryField;

    @FXML
    private TextField addText;

    @FXML
    private ChoiceBox<String> dropDown;

    @FXML
    private Label text;
    private ArrayList<String> listExampleString;


    public void initialize() throws IOException {
        listExampleString = new ArrayList<>();
        processData = new ProcessData();
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
        dropDown.getItems().addAll(pattern);
        if(dropDown.isShowing()) {
            System.out.println("bbbb");
        }



    }

    @FXML
    void closeButton(ActionEvent event) {
        try {
            ApplicationController.goTo("AdminCategory");
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    @FXML
    private void handleExample() {
        System.out.println(dropDown.getValue());
        listExampleString.add(addText.getText());
        String label = "";
        int count = 1;
        for (String temp : listExampleString) {
            label += count + "." + temp + "\n";
            count++;
        }
        text.setWrapText(true);
        text.setText(label);
        addText.clear();
    }

    public void handleSubmitButton() throws IOException {
        String category = addCatagoryField.getText();
        processData.addCategory(category);
        for (String dataLine : listExampleString) {
            if(dropDown.getValue().equals("text")) {
                processData = new ProcessData<>();
                processData.addText(category, dataLine);
            } else if(dropDown.getValue().equals("image")){
                processData = new ProcessData<>();
                processData.addImage(category, dataLine);
            }

        }
        text.setText("");
        addCatagoryField.clear();
        listExampleString.clear();
    }



}
