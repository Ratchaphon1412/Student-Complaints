package ku.cs.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProblemReportController {

    @FXML
    private TextField about;

    @FXML
    private TextField category;

    @FXML
    private TextField date;

    @FXML
    private TextField detail;

    @FXML
    private TextField title;

    public void initialize(){
        String title = this.title.getText();
        String category = this.category.getText();
        String about = this.about.getText();
        String date = this.date.getText();
        String detail = this.detail.getText();


    }

}
