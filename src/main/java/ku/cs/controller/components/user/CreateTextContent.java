package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import ku.cs.State;

import java.io.IOException;
import java.util.prefs.Preferences;

public class CreateTextContent {

    @FXML
    private TextArea inputText;


    @FXML
    private Label titleInput;

    private String text;
    public void initialize() throws IOException {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        titleInput.setFont(font);
        titleInput.setWrapText(true);
    }

    public void setData(String key){
            titleInput.setText(key);

    }

    public String getTextInput() {
        text = inputText.getText().replace("\n"," ");
        return text;
    }
}
