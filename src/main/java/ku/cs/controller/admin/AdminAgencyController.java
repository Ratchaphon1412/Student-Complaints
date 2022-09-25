package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import ku.cs.controller.components.StaffListAgencyController;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.stuff.StuffList;
import ku.cs.service.ProcessData;

import java.io.IOException;

public class AdminAgencyController {
    @FXML
    private GridPane root;

    @FXML
    private GridPane staffListGridPane;
    @FXML
    private GridPane gridPaneAgency;
    private FXMLLoader fxmlLoader;
    private ProcessData processData;
    private StuffList stuffListData;


    @FXML
    public void initialize() throws IOException {
        processData = new ProcessData<>();
        stuffListData = processData.getStuffList();
        fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        root.add(navbar,0,0);


        int i = 1;
        for(Stuff data:stuffListData.getStuffList()) {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staffList.fxml"));
            AnchorPane staffComponant = (AnchorPane) fxmlLoader.load();
            StaffListAgencyController staffListAgencyController = fxmlLoader.getController();
            staffListAgencyController.setData(data);

            staffListGridPane.add(staffComponant, 0, i);
            GridPane.setMargin(staffComponant, new Insets(0,0,5,0));
            i++;
        }

        i = 1;
        for(String temp : stuffListData.getAgency() ) {
            Label label = new Label();
            label.setText(String.valueOf(i) + "."+temp);
            label.setTextFill(Paint.valueOf("#ffffff"));
            gridPaneAgency.add(label, 0, i);
            GridPane.setMargin(label, new Insets(0,0,10,0));
            i++;
        }


    }

    @FXML
    public void addAgencyButton(){

    }

    @FXML
    public void addStuffButton(){

    }
}
