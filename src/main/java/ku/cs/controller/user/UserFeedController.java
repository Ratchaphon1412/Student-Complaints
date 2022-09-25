package ku.cs.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class UserFeedController {


    @FXML
    private GridPane root;
    @FXML
    private GridPane feed;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        root.add(navbar,0,0);

        for(int i = 1 ; i < 5 ; i++) {
            FXMLLoader fxmlLoaderFeed = new FXMLLoader();
            fxmlLoaderFeed.setLocation(getClass().getResource("/ku/cs/components/userFeed.fxml"));
            GridPane feedComponant = (GridPane) fxmlLoaderFeed.load();
            feed.add(feedComponant, 0, i);
            GridPane.setMargin(feedComponant, new Insets(0, 0, 15, 0));
        }
    }
}