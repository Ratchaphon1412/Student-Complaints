package ku.cs.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.ApplicationController;
import ku.cs.controller.components.StaffListAgencyController;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.IOException;

public class AddCategoryController {
    @FXML
    private TextField addCatagoryField;
    @FXML
    private GridPane girdPaneAddCategory;
    private ProcessData processData;
    private FXMLLoader fxmlLoader;


    @FXML
    public void initialize() throws IOException {
        processData = new ProcessData();
        initializeCategory();

    }
    @FXML void initializeCategory() throws IOException {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/categoryList.fxml"));
        AnchorPane categoryComponant = (AnchorPane) fxmlLoader.load();
        AddCategoryController addCategoryController = fxmlLoader.getController();

        GridPane.setMargin(categoryComponant, new Insets(0,0,5,0));
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
    void applyButton(ActionEvent event) throws IOException {
        String category = addCatagoryField.getText();
        processData.addCategory(category);
    }


}
