package ku.cs.controller.components;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ButtonWindowsController {
    @FXML
    private Button close;
    @FXML
    private Button min;
    @FXML
    private Button full;

    private Boolean toggle =false;



    @FXML
    public void closeWindows(){
  Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void minWindows(){
       Stage stage = (Stage)min.getScene().getWindow();
        stage.setIconified(true);

    }
    @FXML
    public void fullWindows(){
    Stage stage = (Stage)full.getScene().getWindow();
        if(!toggle){

            stage.setWidth(1040);
            stage.setHeight(620);
        }else{

            stage.setHeight(580);
            stage.setWidth(1000);
        }
        toggle = (!toggle)? true: false;
    }


}
