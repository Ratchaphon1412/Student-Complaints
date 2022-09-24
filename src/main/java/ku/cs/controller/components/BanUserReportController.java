package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.report.Report;

import java.io.File;

public class BanUserReportController {


    @FXML
    private Circle Img;
    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label titleLabel;


    private Report report;

    public void setData(Report report){
        this.report = report;
        nameLabel.setText(report.getReporter().getUserName());
        timeLabel.setText(report.getTime());
        dateLabel.setText(report.getDate());
        titleLabel.setText(report.getTitle());
        String fs = File.separator;
        File desDir = new File("image" + fs +  "accounts" + fs+ report.getImage());
        Img.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));
    }



}
