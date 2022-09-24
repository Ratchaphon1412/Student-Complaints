package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ku.cs.controller.SwitchTheme;

import java.io.IOException;

public class ButtonThemeController {
    private SwitchTheme switchTheme;


    public void setSwitchTheme(SwitchTheme switchThemeCallback){
        this.switchTheme = switchThemeCallback;
    }

    @FXML
    private void darkMode() throws IOException {
        switchTheme.changeTheme("darkMode");
    }

    @FXML
    private void lightMode() throws IOException {
        switchTheme.changeTheme("lightMode");
    }






}
