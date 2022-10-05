package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.ApplicationController;
import ku.cs.models.admin.Admin;
import ku.cs.service.ProcessData;

import java.io.IOException;

public class AddCategoryController {
    @FXML
    private TextField addCatagoryField;

    private ProcessData<Admin> processData;


    @FXML
    public void initialize() throws IOException {
        processData = new ProcessData<>();
        initializeCategory();

    }
    @FXML void initializeCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/categoryList.fxml"));
        AnchorPane categoryComponant = fxmlLoader.load();
        AddCategoryController addCategoryController = fxmlLoader.getController();

        GridPane.setMargin(categoryComponant, new Insets(0,0,5,0));
    }

    @FXML
    void closeButton() {
        try {
            ApplicationController.goTo("AdminCategory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void applyButton() throws IOException {
        String category = addCatagoryField.getText();
        processData.addCategory(category);
    }


}
