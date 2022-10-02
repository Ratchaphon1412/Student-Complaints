package ku.cs.controller.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StaffController {
    @FXML
    private GridPane root;
    @FXML
    private ScrollPane showDataProblemScrollPane;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staff/navBarStaff.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        root.add(navbar,0,0);
        GridPane gridPanePosts = new GridPane();
        showDataProblemScrollPane.setContent(gridPanePosts);

        for(int i = 0 ; i < 5 ; i++){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staff/staffFeed.fxml"));
            GridPane posts = (GridPane) fxmlLoader.load();
            gridPanePosts.add(posts, 0, i);
            GridPane.setMargin(posts, new Insets(0,0,5,15));
        }


    }

}
