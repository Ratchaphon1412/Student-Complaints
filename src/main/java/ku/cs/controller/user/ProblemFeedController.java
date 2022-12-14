package ku.cs.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import ku.cs.service.DynamicDatabase;
import ku.cs.service.ProcessData;

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
    private Label showDate;
    @FXML
    private Circle imageUser;
    @FXML
    private Label category;
    @FXML
    private Label coutlikeLabel;
    @FXML
    private Button likeButton;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label contentLabel;
    @FXML
    private Label likeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label titleLabel;


    private boolean likeCheck;
    private User user;



    private Report report;



    public void setReport(Report report,User user){
        //font
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        userName.setFont(font);
        content.setFont(font);
        title.setFont(font);
        status.setFont(font);
        showDate.setFont( Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),10));
        coutlikeLabel.setFont(font);
        category.setFont(font);
        contentLabel.setFont(font);
        categoryLabel.setFont(font);
        likeLabel.setFont(font);
        statusLabel.setFont(font);
        titleLabel.setFont(font);
        //get Object
        this.report =report;
        this.user = user;

        //set Like database
        for(String userName: report.getUserNameLike()){
            if(userName.equals(user.getEmail())){
                likeCheck = true;
            }else{
                likeCheck = false;
            }
        }

        refetch();

    }
     @FXML
     public void  viewPost(ActionEvent actionEvent) throws IOException {
            ApplicationController.goToNew("DetailReport",report);
     }
    @FXML
    public void  reportClick(ActionEvent actionEvent) throws IOException {
//        System.out.println("reportUserOrPost");
        ApplicationController.goToNew("reportUserOrPost",report);
    }


        @FXML
        public void like(ActionEvent actionEvent) throws IOException {
          if(likeCheck == true){
            //delete like

              //connect models
              report.deleteLike(user.getEmail());

              DynamicDatabase<Report> dynamicDatabase = new ProcessData<>();
              dynamicDatabase.changeData(report,"like");
              likeCheck = !likeCheck;
              refetch();


          }else{
              //add

              //connect models
              report.addLike(user.getEmail());

              DynamicDatabase<Report> dynamicDatabase = new ProcessData<>();
              dynamicDatabase.changeData(report,"like");
              likeCheck = !likeCheck;
              refetch();


          }


        }

        private void refetch(){
            LinkedHashMap<String, LinkedHashMap<String, String>> temp = report.getCategory().getMapDataPattern();
            LinkedHashMap<String,String> temp2= temp.get("text");
            String[] keyContent = temp2.keySet().toArray(String[]::new);

            //set Text
            userName.setText(report.getReporter().getUserName());
            content.setText(temp2.get(keyContent[0]));
            title.setText(report.getTitle());
            status.setText(report.getReportStage());
            category.setText(report.getCategory().getNameCategory());
            showDate.setText(report.getProblemDate());
            coutlikeLabel.setText(String.valueOf(report.getCountLike()));


            //set image
            File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+report.getReporter().getPathPicture());
            Image image = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

            if (!image.isError()){
                imageUser.setFill(new ImagePattern(image));
                imageUser.setStroke(Color.TRANSPARENT);
            }

            if(likeCheck == true){
                likeButton.getStyleClass().remove("likeSVG");
                likeButton.getStyleClass().remove("colorIcon");
                likeButton.getStyleClass().add("likedSVG");
                likeButton.getStyleClass().add("colorIcon");
            }else{
                likeButton.getStyleClass().remove("likedSVG");
                likeButton.getStyleClass().remove("colorIcon");
                likeButton.getStyleClass().add("likeSVG");
                likeButton.getStyleClass().add("colorIcon");
            }

        }
}
