package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReportText {

    @FXML
    private Label key;

    @FXML
    private Label text;

    public void setDataText(String keyData ,String details){
        key.setText(keyData);
        text.setText(details);

    }

}
