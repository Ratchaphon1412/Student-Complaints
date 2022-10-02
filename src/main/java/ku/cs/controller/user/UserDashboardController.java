package ku.cs.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.NavbarUser;
import ku.cs.models.report.Filterer;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.prefs.Preferences;

public class UserDashboardController {


    @FXML
    private GridPane root;
    @FXML
    private GridPane feed;


    @FXML
    private Label userName;

    @FXML
    private Label roleUser;

    @FXML
    private Circle imageAccount;

    @FXML
    private GridPane minisetting;

    @FXML
    private Label titleFeed;

    @FXML
    private Label titleSort;

    @FXML
    private ChoiceBox sortChoice;

    @FXML
    private ChoiceBox choiceSort2;

    @FXML
    private Label titleRangeVote;

    @FXML
    private TextField lessNum;

    @FXML
    private TextField mostNum;



    private SwitchTheme changeTheme;

    private ProcessData processData;

    private ReportList reportLists;

    private List<Report> reportSorted;
    private String choiceSort;


    private User user;

    @FXML
    public void initialize() throws IOException {
        processData = new ProcessData<>();
        reportLists = processData.getReportList();
        reportSorted = new ArrayList<>();
        reportSorted = reportLists.getReportLists();

       //get pattern for get category
       List<LinkedHashMap<String,String>> patternList = processData.getDataBase().getPatternList();
        ArrayList<String> choiceSort = new ArrayList<>();
        choiceSort.add("All");
        for(LinkedHashMap<String,String>temp : patternList){
            choiceSort.add(temp.get("category"));
        }
        sortChoice.getItems().addAll(choiceSort);
        ArrayList<String> sortChoiceVote = new ArrayList<>();
        sortChoiceVote.add("New Post");
        sortChoiceVote.add("Old Post");
        sortChoiceVote.add("Most Like");
        sortChoiceVote.add("Least Like");
        choiceSort2.getItems().addAll(sortChoiceVote);


        //getObject from router
        user = (User) ApplicationController.getData();
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        root.getStylesheets().add(getClass().getResource(style).toExternalForm());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        //set label
        userName.setText(user.getUserName());
        roleUser.setText(user.getRole());
        userName.setFont(font);
        roleUser.setFont(font);
        titleFeed.setFont(font);
        titleSort.setFont(font);
        titleRangeVote.setFont(font);


        //navbar
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarUser.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarUser navbarUser = fxmlLoader.getController();
        navbarUser.setUser(user);
        root.add(navbar,0,0);


        //set image
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+user.getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()),500,0,true,true);
        if (!image.isError()){
            imageAccount.setFill(new ImagePattern(image));
            imageAccount.setStroke(Color.TRANSPARENT);
        }
        //implements interface callback
        changeTheme = new SwitchTheme() {
            @Override
            public void changeTheme(String theme) throws IOException {
                //save theme
                State state = new State();
                state.setTempData();
                state.saveThemeToConfig(theme);
                //change state
                Preferences preferences = Preferences.userRoot().node(State.class.getName());
                preferences.put("theme",theme);
                //change stylesheet in main page
                root.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
                root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
                root.getStylesheets().add(getClass().getResource(style).toExternalForm());
            }
        };

        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = (GridPane)fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);
        refetch();
    }

    @FXML
    public void handleUserSettingButton(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("UserSetting",user);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void refetch() throws IOException {
        feed.getChildren().clear();
        //show report
        int i = 0;
        for(Report report : reportSorted) {
            FXMLLoader fxmlLoaderFeed = new FXMLLoader();
            fxmlLoaderFeed.setLocation(getClass().getResource("/ku/cs/components/userFeed.fxml"));
            GridPane feedComponant = (GridPane) fxmlLoaderFeed.load();
            ProblemFeedController problemFeedController = fxmlLoaderFeed.getController();
            problemFeedController.setReport(report,user);
            GridPane.setMargin(feedComponant, new Insets(0, 0, 15, 0));
            feed.add(feedComponant, 0, i+1);
            i++;
        }

    }


    @FXML
    public void buttonSort(ActionEvent actionEvent) throws IOException {
        String choice = sortChoice.getValue().toString();

        if(!choice.equals("All")){
            //set sort
            sort(choiceSort,reportLists.getReportLists());
            reportLists.setReportSetterSort(reportLists.getReportLists());
            ReportList reportpare = reportLists.sortReport(new Filterer<Report>() {
                @Override
                public boolean filter(Report report) {
                    if(report.getCategory().getNameCategory().equals(choice)){
                        return true;
                    }
                    return false;
                }
            });
            if(reportpare.getReportSort().size() != 0){
                reportSorted = reportpare.getReportSort();
                refetch();
            }else{
                List<Report> temp = reportSorted;
                reportSorted = reportpare.getReportSort();
                refetch();
                reportSorted = temp;
            }
            lessNum.clear();
            mostNum.clear();
        }else{
            reportSorted = reportLists.getReportLists();
            refetch();
        }
    }
    @FXML
    private void sortButtonVote() throws IOException {
        choiceSort = choiceSort2.getValue().toString();

        sort(choiceSort,reportSorted);

    }

    private void sort(String choice,List<Report> reportSort) throws IOException {
        switch (choice){
            case "New Post":{
                Comparator newPost = new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Report p1 = (Report) o1;
                        Report p2 = (Report) o2;
                        return p2.getDate().compareTo(p1.getDate());
                    }
                };
                Collections.sort(reportSort,newPost);
                refetch();
                break;
            }
            case "Old Post":{
                Comparator oldPost = new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Report p1 = (Report) o1;
                        Report p2 = (Report) o2;
                        return p1.getDate().compareTo(p2.getDate());
                    }
                };
                Collections.sort(reportSort,oldPost);
                refetch();
                break;
            }
            case "Most Like":{
                Comparator mostLike = new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Report p1 = (Report) o1;
                        Report p2 = (Report) o2;
                        return p2.getCountLike()-p1.getCountLike();
                    }
                };

                Collections.sort(reportSort,mostLike);
                refetch();
                break;
            }
            case "Least Like":{

                Comparator leastLike = new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Report p1 = (Report) o1;
                        Report p2 = (Report) o2;
                        return p1.getCountLike()-p2.getCountLike();
                    }
                };
                Collections.sort(reportSort,leastLike);
                refetch();
                break;
            }

        }
    }


    @FXML
    private void sortVote(ActionEvent actionEvent) throws IOException {
        String less = lessNum.getText();
        String most = mostNum.getText();

        if(!most.equals("") && !less.equals("")){
            int lessInt = Integer.parseInt(less);
            int mostInt = Integer.parseInt(most);
            System.out.println(lessInt);
            System.out.println(mostInt);
            reportLists.setReportSetterSort(reportSorted);
            ReportList reportpare = reportLists.sortReport(new Filterer<Report>() {
                @Override
                public boolean filter(Report report) {
                    if(report.getCountLike()>=lessInt && report.getCountLike()<=mostInt){
                        return true;
                    }
                    return false;
                }
            });
            if(reportpare.getReportSort().size() != 0){
                reportSorted = reportpare.getReportSort();
                refetch();
            }else{
                List<Report> temp = reportSorted;
                reportSorted = reportpare.getReportSort();
                refetch();
                reportSorted = temp;
            }



        }else if(!most.equals("") && less.equals("")){
            int mostInt = Integer.parseInt(most);
            System.out.println(mostInt);
            reportLists.setReportSetterSort(reportSorted);
            ReportList reportpare = reportLists.sortReport(new Filterer<Report>() {
                @Override
                public boolean filter(Report report) {
                    if(report.getCountLike()>mostInt){
                        return true;
                    }
                    return false;
                }
            });
            if(reportpare.getReportSort().size() != 0){
                reportSorted = reportpare.getReportSort();
                refetch();
            }else{
                List<Report> temp = reportSorted;
                reportSorted = reportpare.getReportSort();
                refetch();
                reportSorted = temp;
            }
        }



    }

}