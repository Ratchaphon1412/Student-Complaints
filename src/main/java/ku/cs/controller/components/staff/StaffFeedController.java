package ku.cs.controller.components.staff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.report.Report;

public class StaffFeedController {

    @FXML
    private Label content;

    @FXML
    private Label nameAgency;

    @FXML
    private Label nameStaff;

    @FXML
    private Label status;

    @FXML
    private Label title;

    private Report report;


    public void setData(Report report){
        this.report = report;

    }

}
