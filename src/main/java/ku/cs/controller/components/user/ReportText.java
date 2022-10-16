package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ku.cs.State;

import java.util.prefs.Preferences;

public class ReportText {

    @FXML
    private Label key;

    @FXML
    private Label text;

    public void setDataText(String keyData ,String details){

        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        key.setFont(font);
        text.setFont(font);
        key.setWrapText(true);
        text.setWrapText(true);
        key.setText(keyData);
        text.setText(details);

    }

}
