package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CreateTextContent {

    @FXML
    private TextArea inputText;


    @FXML
    private Label titleInput;

    private String text;

    public void setData(String key){
            titleInput.setText(key);

    }

    public String getTextInput() {
        text = inputText.getText().replace("\n"," ");
        return text;
    }
}
