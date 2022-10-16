package ku.cs.controller.admin;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInUp;
import animatefx.animation.ZoomIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ku.cs.ApplicationController;


import ku.cs.State;
import ku.cs.controller.Reposthable;
import ku.cs.models.admin.Admin;

import ku.cs.service.DataBase;

import ku.cs.service.DynamicDatabase;
import ku.cs.service.ProcessData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

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
    @FXML
    private Label categoryLabel;
    @FXML
    private Label addCategoryLabel;
    @FXML
    private Label agencyLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private GridPane gridPaneAddCategory;

    @FXML
    private Label error;


    private ArrayList<String> listExampleString;
    private ArrayList<String> textString;
    private  ArrayList<String> imageString;
    private ArrayList<String> checkType;
    private ArrayList<String> dropDownAgencyList;
    DataBase dataBase;
    private Reposthable reposthable;



    public void initialize() throws IOException {

        dataBase = new DataBase();
        listExampleString = new ArrayList<>();
        processData = new ProcessData();
        textString = new ArrayList<>();
        imageString = new ArrayList<>();
        checkType = new ArrayList<>();
        dropDownAgencyList = new ArrayList<>();
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" + preferences.get("theme", null) + ".css";
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        gridPaneAddCategory.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        gridPaneAddCategory.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        gridPaneAddCategory.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        categoryLabel.setFont(font);
        addCategoryLabel.setFont(font);
        agencyLabel.setFont(font);
        typeLabel.setFont(font);
        //set Animation
        new FadeInUp(gridPaneAddCategory).play();

        initializeCategory();



    }


    @FXML
    void initializeCategory() throws IOException {
//        fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/categoryList.fxml"));
//        AnchorPane categoryComponant = (AnchorPane) fxmlLoader.load();
//        AddCategoryController addCategoryController = fxmlLoader.getController();
//
//        GridPane.setMargin(categoryComponant, new Insets(0,0,5,0));
       reposthable =(Reposthable) ApplicationController.getData();
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
        error.setText("");
        String type = dropDownType.getValue();
        int count = 1;
        if(addCatagoryField.getText() != "" && dropDownAgency.getValue() != null && addText.getText() != "" && type != null) {

                        if (dropDownAgency.getValue() != null)
                            listExampleString.add(addText.getText());

                        String label = "";

                        if (type.equals("text")) {
                            textString.add(addText.getText());
                            checkType.add(" text");
                            for (String temp : listExampleString) {
                                label += count + "." + temp + checkType.get(count - 1) + "\n";
                                count++;
                            }
                            text.setWrapText(true);
                            text.setText(label);
                            addText.clear();


                        } else if (type.equals("image")) {
                            imageString.add(addText.getText());
                            checkType.add(" image");
                            for (String temp : listExampleString) {
                                label += count + "." + temp + checkType.get(count - 1) + "\n";
                                count++;
                            }
                            text.setWrapText(true);
                            text.setText(label);
                            addText.clear();

                        }

        }else{error.setText("โปรดใส่ข้อมูลให้ครบถ้วน");}

    }

    public void handleSubmitButton() throws IOException {
        if(addCatagoryField.getText() != "") {
            if(!listExampleString.isEmpty()) {
                String category = addCatagoryField.getText();
                processData.addCategory(category);

                for (String dataLine : textString) {
                    processData = new ProcessData<>();
                    processData.addText(category, dataLine);
                }

                for (String dataLine : imageString) {
                    processData = new ProcessData<>();
                    processData.addImage(category, dataLine);
                }
                processData.selectAgency(category, dropDownAgency.getValue());
                reposthable.refreshPost();
                clear();
                closeWindows();
            }
        }


    }
    public void clear(){
        text.setText("");
        listExampleString.clear();
        textString.clear();
        imageString.clear();
        checkType.clear();
    }

    @FXML
    public void closeWindows() throws IOException {

        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }



}
