package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.stuff.Stuff;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class StaffListAgencyController {

    @FXML
    private Circle imageStuff;


    @FXML
    private Label AgencyLabel;

    @FXML
    private Label nameStuffLabel;
    private Stuff stuff;

    public void setData(Stuff stuff) throws IOException {
        this.stuff = stuff;
        AgencyLabel.setText(stuff.getAgency());
        nameStuffLabel.setText(stuff.getUserName());
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ stuff.getPathPicture());
        imageStuff.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));


//        URL url = new URL("image" + fs +  "accounts" + fs+ stuff.getPathPicture());
//        URLConnection connection = url.openConnection();
//        InputStream inputStream = connection.getInputStream();
//        imageStuff.setFill(new ImagePattern(new Image(inputStream,800, 0 ,true,true)));
    }
}
