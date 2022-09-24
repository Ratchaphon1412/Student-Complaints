package ku.cs.controller.stuff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AgencyController {
    @FXML
    private GridPane root;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        root.add(navbar,0,0);
    }

    @FXML
    public void addAgencyButton(){

    }

    @FXML
    public void addStuffButton(){

    }
}
