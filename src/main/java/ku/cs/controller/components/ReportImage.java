package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class ReportImage {

    @FXML
    private Label key;
    @FXML
    private ImageView imageReport;

    public void setDataImage(String keyData,String imageData){
        key.setText(keyData);

        //get picture from objectAdmin
        File desDir = new File("image"+System.getProperty("file.separator")+"reports"+System.getProperty("file.separator")+imageData);
        Image imageAccount = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

        if (!imageAccount.isError()){
            imageReport.setImage(imageAccount);
        }

    }


}
