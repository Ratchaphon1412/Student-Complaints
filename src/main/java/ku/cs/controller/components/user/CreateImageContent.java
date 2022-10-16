package ku.cs.controller.components.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import ku.cs.State;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class CreateImageContent {

    @FXML
    private Label titleInput;
    @FXML
    private Label pathLabel;
    @FXML
    private ImageView imagePicture;
    private File file;
    public void initialize() throws IOException {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        titleInput.setFont(font);
        titleInput.setWrapText(true);
        pathLabel.setFont(font);
        pathLabel.setWrapText(true);
    }

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
            imagePicture.setImage(new Image(String.valueOf(file.toURI())));
        }
    }

    public void setData(String key){
        titleInput.setText(key);
    }

    public File getFile() {
        return file;
    }
}
