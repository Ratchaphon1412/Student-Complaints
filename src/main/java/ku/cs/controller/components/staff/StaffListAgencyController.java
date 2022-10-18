package ku.cs.controller.components.staff;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.State;
import ku.cs.controller.admin.AgencyLoad;
import ku.cs.models.staff.Staff;
import ku.cs.models.staff.StaffList;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
    private AgencyLoad agencyLoad;

    private ProcessData<Staff> processData;
    private StaffList staffList;


    private Staff staff;

    public void setData(Staff staff,AgencyLoad agencyLoad) throws IOException {
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        this.staff = staff;
        AgencyLabel.setText(staff.getAgency());
        nameStuffLabel.setText(staff.getUserName());
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ staff.getPathPicture());
        imageStuff.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));

        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),10);
        nameStuffLabel.setFont(font);
        AgencyLabel.setFont(font);
        this.agencyLoad = agencyLoad;

        processData = new ProcessData<>();

        staffList = new StaffList(processData.getDataBase().getAccountList(),processData.getDataBase().getAgencyList());
//        int i = 0;
//        for(String temp: staffList.getAgency()){
//            if(temp.equals(staff.getAgency())){
//                staffList.getAgency().remove();
//            }
//            i++;
//        }
        staffList.getAgency().remove(staff.getAgency());
        choiceAgency.getItems().addAll(staffList.getAgency());




    }

    @FXML
    private void submitChangeAgency() throws IOException {
        String selectAgency = "";
        if(choiceAgency.getValue() != null){
            selectAgency = choiceAgency.getValue().toString();
        }
         if(!selectAgency.equals(staff.getAgency())){
             staff.setAgency(selectAgency);
             processData.changeData(staff,"changeAgency");
             agencyLoad.reloadAgency();
//             no refetch
//             AgencyLabel.setText(selectAgency);
         }
         choiceAgency.setValue("");

    }

}
