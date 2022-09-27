package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.State;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.stuff.StuffList;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.prefs.Preferences;

public class StaffListAgencyController {

    @FXML
    private Circle imageStuff;


    @FXML
    private Label AgencyLabel;

    @FXML
    private Label nameStuffLabel;

    @FXML
    private ChoiceBox choiceAgency;

    private ProcessData<Stuff> processData;
    private StuffList stuffList;


    private Stuff stuff;

    public void setData(Stuff stuff) throws IOException {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        this.stuff = stuff;
        AgencyLabel.setText(stuff.getAgency());
        nameStuffLabel.setText(stuff.getUserName());
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ stuff.getPathPicture());
        imageStuff.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));

        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),10);
        nameStuffLabel.setFont(font);
        AgencyLabel.setFont(font);


        processData = new ProcessData<>();

        stuffList = new StuffList(processData.getDataBase().getAccountList(),processData.getDataBase().getAgencyList());
        choiceAgency.getItems().addAll(stuffList.getAgency());




    }

    @FXML
    private void submitChangeAgency() throws IOException {
        String selectAgency = "";
        if(choiceAgency.getValue() != null){
            selectAgency = choiceAgency.getValue().toString();
        }
        stuff.setAgency(selectAgency);
        processData.changeData(stuff,"changeAgency");

        //no refetch
        AgencyLabel.setText(selectAgency);


    }

}
