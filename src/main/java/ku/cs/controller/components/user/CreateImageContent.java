package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class CreateImageContent {

    @FXML
    private Label titleInput;

    @FXML
    private Label pathLabel;

    @FXML
    private ImageView imagePicture;

    private File file;

    @FXML
    private void selectImageButton(){
        FileChooser choosefile = new FileChooser();
        ArrayList<String> listfile = new ArrayList<>();
        listfile.add("*.jpg");
        listfile.add("*.png");
        listfile.add("*jpeg");
        choosefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Picture", listfile));
        file = choosefile.showOpenDialog(null);
        if(file != null){
            pathLabel.setText(file.getAbsolutePath());
            imagePicture.setImage(new Image(file.getAbsolutePath()));
        }
    }

    public void setData(String key){
        titleInput.setText(key);
    }

    public File getFile() {
        return file;
    }
}
