package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.user.Reposthable;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.prefs.Preferences;

public class CreatePost {
    @FXML
    private GridPane root;

    @FXML
    private TextField titleText;

    @FXML
    private ChoiceBox<String> category;

    @FXML
    private ScrollPane scrollContent;


    @FXML
    private Label title;

    @FXML
    private Label titleCategory;
    @FXML
    private Label titleContent;

    @FXML
    private Button closeButton;

    private ProcessData<User> processData;

    private List<LinkedHashMap<String,String>> pattern;

    private ArrayList<CreateTextContent>  controllerCreateTextContentList;
    private ArrayList<CreateImageContent> controllerCreateImageContentList;

    private String selectedCategory;
    private String agency;
    private User user;
    private Reposthable reposthable;

    @FXML
    private void initialize(){
        user = (User) ApplicationController.getData();
        reposthable = (Reposthable) ApplicationController.getRefreshable();

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
        title.setFont(font);
        titleCategory.setFont(font);
        titleContent.setFont(font);

        //connect database
        processData = new ProcessData<>();
        pattern = processData.getDataBase().getPatternList();
        List<String> categoryList = new ArrayList<>();
        for(LinkedHashMap<String,String> temp : pattern){
             categoryList.add(temp.get("category"));
        }
        //choice box
        category.getItems().addAll(categoryList);



    }

    @FXML
    private void submit() throws IOException, RefreshFailedException {
        if(titleText.getText().equals("") || category.getValue() == null){
            return;
        }
        String title = titleText.getText();
        String categoryText = category.getValue();
        ArrayList<String> dataTextList = new ArrayList<>();
        ArrayList<File> dataImage = new ArrayList<>();
        for(CreateTextContent createTextContent :controllerCreateTextContentList ){
            if(createTextContent.getTextInput().equals("")){
                return;
            }
            dataTextList.add(createTextContent.getTextInput());
        }

        for(CreateImageContent createImageContent : controllerCreateImageContentList){
            if(createImageContent.getFile() == null){
                return;
            }
            dataImage.add(createImageContent.getFile());
        }

        processData.createPost(title,user,categoryText,agency,dataTextList,dataImage);
        reposthable.refreshPost();
        close();

    }


    @FXML
    private void selectCategory() throws IOException {
        selectedCategory = category.getValue();
        LinkedHashMap<String,String> patternCategory = new LinkedHashMap<>();
        //loop find category
        for (LinkedHashMap<String,String> temp :pattern){
            if(temp.get("category").equals(selectedCategory)){
                patternCategory = temp;
                agency = temp.get("agency");
            }
        }
        String[] patternText = patternCategory.get("text").split("\\|");
        String[] patternImage = patternCategory.get("image").split("\\|");

        controllerCreateTextContentList = new ArrayList<>();
        controllerCreateImageContentList = new ArrayList<>();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(100);
        scrollContent.setFitToWidth(true);
        scrollContent.setContent(gridPane);

        int row = 0;
        for(String temp : patternText){
           if(!temp.equals("")){
               FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/createTextContent.fxml"));
               GridPane gridPanetemp = fxmlLoader.load();

               CreateTextContent createTextContent = fxmlLoader.getController();
               createTextContent.setData(temp);
               // store controller
               controllerCreateTextContentList.add(createTextContent);
               gridPane.setMargin(gridPanetemp,new Insets(0,0,0,0));
               gridPane.add(gridPanetemp,0,row);
               row++;
           }
        }

        for(String temp : patternImage){
            if(!temp.equals("")){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/createImageContent.fxml"));
                GridPane gridPanetemp = fxmlLoader.load();

                CreateImageContent createImageContent =fxmlLoader.getController();
                createImageContent.setData(temp);

                controllerCreateImageContentList.add(createImageContent);
                gridPane.setMargin(gridPanetemp,new Insets(0,0,0,0));
                gridPane.add(gridPanetemp,0,row);
                row++;
            }
        }

    }

    @FXML
    private void close(){
         Stage stage= (Stage)closeButton.getScene().getWindow();
         stage.close();
    }


}