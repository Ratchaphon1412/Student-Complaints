package ku.cs.controller.user;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.navbar.NavbarUserController;
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
//    @FXML
//    private GridPane feed;


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
    private ChoiceBox<String> sortChoice;

    @FXML
    private ChoiceBox<String> choiceSort2;

    @FXML
    private Label titleRangeVote;

    @FXML
    private TextField lessNum;

    @FXML
    private TextField mostNum;
    @FXML
    private ScrollPane  scrollFeed;


    private ReportList reportLists;

    private List<Report> reportSorted;
    private String choiceSort;
    private String choiceSortCategory;


    private User user;

    @FXML
    public void initialize() throws IOException {
        ProcessData<User> processData = new ProcessData<>();
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
        choiceSort2.setValue("Old Post");
        sortChoice.setValue("All");
        this.choiceSortCategory = "All";
        this.choiceSort = "Old Post";


        //getObject from router
        user = (User) ApplicationController.getData();
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(styleTheme)).toExternalForm());
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(icon)).toExternalForm());
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(style)).toExternalForm());
        Font font =  Font.loadFont(Objects.requireNonNull(getClass().getResource("/ku/cs/assets/fonts/" + preferences.get("font", null))).toExternalForm(),15);
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
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/navBarUser.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarUserController navbarUserController = fxmlLoader.getController();
        navbarUserController.setUser(user);
        root.add(navbar,0,0);


        //set image
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+user.getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()),500,0,true,true);
        if (!image.isError()){
            imageAccount.setFill(new ImagePattern(image));
            imageAccount.setStroke(Color.TRANSPARENT);
        }
        //implements interface callback
        //save theme
        //change state
        //change stylesheet in main page
        SwitchTheme changeTheme = new SwitchTheme() {
            @Override
            public void changeTheme(String theme) throws IOException {
                //save theme
                State state = new State();
                state.setTempData();
                state.saveThemeToConfig(theme);
                //change state
                Preferences preferences = Preferences.userRoot().node(State.class.getName());
                preferences.put("theme", theme);
                //change stylesheet in main page
                root.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" + preferences.get("theme", null) + ".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(styleTheme)).toExternalForm());
                root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(icon)).toExternalForm());
                root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(style)).toExternalForm());
            }
        };

        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);
        refetch();
    }

    @FXML
    public void handleUserSettingButton() {
        try {
            ApplicationController.goTo("UserSetting",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refetch() throws IOException {
        GridPane feed = new GridPane();
        feed.setHgap(10);
//        feed.setVgap(10);
//        feed.setMaxHeight(340);
//        feed.setMaxWidth(550);

        scrollFeed.setFitToWidth(true);
        scrollFeed.setContent(feed);
        feed.getChildren().clear();
        //show report
        int i = 0;
        for(Report report : reportSorted) {
            FXMLLoader fxmlLoaderFeed = new FXMLLoader();
            fxmlLoaderFeed.setLocation(getClass().getResource("/ku/cs/components/user/userFeed.fxml"));
            GridPane feedComponant = fxmlLoaderFeed.load();
            ProblemFeedController problemFeedController = fxmlLoaderFeed.getController();
            problemFeedController.setReport(report,user);
            feed.add(feedComponant, 0, i+1);
            feed.setMargin(feedComponant, new Insets(0, 3, 5, 0));
            i++;
        }

    }


    @FXML
    public void buttonSort() throws IOException {
        String choice = sortChoice.getValue();
        this.choiceSortCategory = choice;
        sortCategory(choice);
    }
    @FXML
    private void sortButtonVote() throws IOException {
        choiceSort = choiceSort2.getValue();

        sortColletion(choiceSort,reportSorted);

    }

    private void sortCategory(String choice) throws IOException {
        if(!choice.equals("All")){
            //set sort
            sortColletion(choiceSort,reportLists.getReportLists());
            reportLists.setReportSetterSort(reportLists.getReportLists());
            ReportList reportpare = reportLists.sortReport(report -> report.getCategory().getNameCategory().equals(choice));
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
    private void sortColletion(String choice, List<Report> reportSort) throws IOException {
        switch (choice) {
            case "New Post" -> {
                Comparator newPost = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p2.getDate().compareTo(p1.getDate());
                };
                reportSort.sort(newPost);
                refetch();
            }
            case "Old Post" -> {
                Comparator oldPost = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p1.getDate().compareTo(p2.getDate());
                };
                reportSort.sort(oldPost);
                refetch();

            }
            case "Most Like" -> {
                Comparator mostLike = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p2.getCountLike() - p1.getCountLike();
                };

                reportSort.sort(mostLike);
                refetch();

            }
            case "Least Like" -> {

                Comparator leastLike = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p1.getCountLike() - p2.getCountLike();
                };
                reportSort.sort(leastLike);
                refetch();

            }
        }
    }


    @FXML
    private void sortVote() throws IOException {
        String less = lessNum.getText();
        String most = mostNum.getText();
        sortCategory(choiceSortCategory);
        if(!most.equals("") && !less.equals("")){
            int lessInt = Integer.parseInt(less);
            int mostInt = Integer.parseInt(most);
            System.out.println(lessInt);
            System.out.println(mostInt);
            reportLists.setReportSetterSort(reportSorted);
            ReportList reportpare = reportLists.sortReport(report -> report.getCountLike() >= lessInt && report.getCountLike() <= mostInt);
            if(reportpare.getReportSort().size() != 0){
                reportSorted = reportpare.getReportSort();
                refetch();
            }else{
                List<Report> temp = reportSorted;
                reportSorted = reportpare.getReportSort();
                refetch();
                reportSorted = temp;
            }



        }else if(!most.equals("")){
            int mostInt = Integer.parseInt(most);
            System.out.println(mostInt);
            reportLists.setReportSetterSort(reportSorted);
            ReportList reportpare = reportLists.sortReport(report -> report.getCountLike() > mostInt);
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