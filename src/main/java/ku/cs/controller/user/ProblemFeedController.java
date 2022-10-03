package ku.cs.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.prefs.Preferences;

public class ProblemFeedController {

    @FXML
    private Label userName;

    @FXML
    private Label title;

    @FXML
    private Label content;

    @FXML
    private Label status;

    @FXML
    private Circle imageUser;




    private Report report;



    public void setReport(Report report){
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        userName.setFont(font);
        content.setFont(font);
        title.setFont(font);
        status.setFont(font);
        this.report =report;
        userName.setText(report.getReporter().getUserName());
//        LinkedHashMap<String,List<LinkedHashMap<String,String>>> temp = report.getCategory().getMapDataPattern();
        LinkedHashMap<String, LinkedHashMap<String, String>> temp = report.getCategory().getMapDataPattern();
        LinkedHashMap<String,String> temp2= temp.get("text");
        String[] keyContent = temp2.keySet().toArray(String[]::new);


        content.setText(temp2.get(keyContent[0]));
        title.setText(report.getTitle());
        status.setText(report.getReportStage());

        //set image
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+report.getReporter().getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

        if (!image.isError()){
            imageUser.setFill(new ImagePattern(image));
            imageUser.setStroke(Color.TRANSPARENT);
        }

    }
     @FXML
     public void  viewPost(ActionEvent actionEvent) throws IOException {
            ApplicationController.goToNew("DetailReport",report);
     }

     @FXML
     public void like(ActionEvent actionEvent){
        System.out.println("test");
     }


}
