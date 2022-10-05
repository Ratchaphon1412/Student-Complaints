package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.ApplicationController;
import ku.cs.models.admin.Admin;
import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AddCategoryController {

    @FXML
    private TextField addCatagoryField;

    private ProcessData<Admin> processData;

    @FXML
    private TextField addText;

    @FXML
    private ChoiceBox<String> dropDown;

    @FXML
    private Label text;
    private ArrayList<String> listExampleString;


    public void initialize() throws IOException {
        processData = new ProcessData<>();
        initializeCategory();




        //initializeCategory();

    }
    @FXML void initializeCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/categoryList.fxml"));
        AnchorPane categoryComponant = fxmlLoader.load();
        AddCategoryController addCategoryController = fxmlLoader.getController();

    }

    @FXML
    void closeButton() {
        try {
            ApplicationController.goTo("AdminCategory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    void applyButton() throws IOException {
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
