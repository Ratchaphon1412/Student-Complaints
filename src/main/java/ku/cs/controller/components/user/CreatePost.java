package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ku.cs.State;

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
    private Button submit;

    @FXML
    private Label title;

    @FXML
    private Label titleCategory;
    @FXML
    private Label titleContent;

    @FXML
    private Label titleSelectCategory;


    @FXML
    private void initialize(){
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
        titleSelectCategory.setFont(font);

    }


}