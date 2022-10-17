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
import ku.cs.service.SortReport;

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
    private ScrollPane scrollFeed;

    @FXML
    private ChoiceBox<String> processChoice;


    private ReportList reportLists;

    private List<Report> reportSorted;
    private String choiceSort;
    private String choiceSortCategory;
    private String choiceSortProcess;


    private User user;

    @FXML
    public void initialize() throws IOException {
        ProcessData<User> processData = new ProcessData<>();
        //get reportlist
        reportLists = processData.getReportList();

        reportSorted = new ArrayList<>();
        reportSorted = reportLists.getReportLists();
        this.choiceSortCategory = "All";
        this.choiceSort = "Old Post";
        this.choiceSortProcess = "All";

        //set reportsort
        SortReport.setReportSort(reportSorted);
        //sort
        SortReport.sortProcess(choiceSortProcess);
        SortReport.sortCategory(choiceSortCategory);
        SortReport.sortCollection(this.choiceSort);
        reportSorted = SortReport.getReportSort();

        //get pattern for get category
        List<LinkedHashMap<String, String>> patternList = processData.getDataBase().getPatternList();
        ArrayList<String> choiceSort = new ArrayList<>();
        choiceSort.add("All");
        for (LinkedHashMap<String, String> temp : patternList) {
            choiceSort.add(temp.get("category"));
        }
        sortChoice.getItems().addAll(choiceSort);
        //choice process
        ArrayList<String> sortChoiceProcess = new ArrayList<>();
        sortChoiceProcess.add("All");
        sortChoiceProcess.add("in queue");
        sortChoiceProcess.add("in progress");
        sortChoiceProcess.add("finish");
        processChoice.getItems().addAll(sortChoiceProcess);
        processChoice.setValue("All");

        ArrayList<String> sortChoiceVote = new ArrayList<>();
        sortChoiceVote.add("New Post");
        sortChoiceVote.add("Old Post");
        sortChoiceVote.add("Most Like");
        sortChoiceVote.add("Least Like");
        choiceSort2.getItems().addAll(sortChoiceVote);
        choiceSort2.setValue("Old Post");
        sortChoice.setValue("All");



        //getObject from router
        user = (User) ApplicationController.getData();
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" + preferences.get("theme", null) + ".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(styleTheme)).toExternalForm());
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(icon)).toExternalForm());
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(style)).toExternalForm());
        Font font = Font.loadFont(Objects.requireNonNull(getClass().getResource("/ku/cs/assets/fonts/" + preferences.get("font", null))).toExternalForm(), 15);
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
        root.add(navbar, 0, 0);


        //set image
        File desDir = new File("image" + System.getProperty("file.separator") + "accounts" + System.getProperty("file.separator") + user.getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()), 500, 0, true, true);
        if (!image.isError()) {
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
        minisetting.add(switchTheme, 1, 1);
        refetch();
    }

    @FXML
    public void handleUserSettingButton() {
        try {
            ApplicationController.goTo("UserSetting", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refetch() throws IOException {
        GridPane feed = new GridPane();
        feed.setHgap(10);

        scrollFeed.setFitToWidth(true);
        scrollFeed.setContent(feed);
        feed.getChildren().clear();
        //show report
        int i = 0;
        for (Report report : reportSorted) {
            FXMLLoader fxmlLoaderFeed = new FXMLLoader();
            fxmlLoaderFeed.setLocation(getClass().getResource("/ku/cs/components/user/userFeed.fxml"));
            GridPane feedComponant = fxmlLoaderFeed.load();
            ProblemFeedController problemFeedController = fxmlLoaderFeed.getController();
            problemFeedController.setReport(report, user);
            feed.add(feedComponant, 0, i + 1);
            feed.setMargin(feedComponant, new Insets(0, 3, 5, 0));
            i++;
        }

    }

    @FXML
    private void buttonSortProcess() throws IOException {
        //set global
        choiceSortProcess = processChoice.getValue();
        reportSorted = reportLists.getReportLists();
        SortReport.setReportSort(reportSorted);
        SortReport.sortCollection(choiceSort);
        SortReport.sortCategory(choiceSortCategory);
        if(!choiceSortProcess.equals("All")){
            SortReport.sortProcess(choiceSortProcess);
            reportSorted = SortReport.getReportSort();
            refetch();

        }else{
            reportSorted = SortReport.getReportSort();
            refetch();
        }
        //clear number vote
        lessNum.clear();
        mostNum.clear();

    }

    @FXML
    private void sortButtonCollection() throws IOException {
        //collection
        choiceSort = choiceSort2.getValue();
        reportSorted = reportLists.getReportLists();
        SortReport.setReportSort(reportSorted);
        SortReport.sortProcess(choiceSortProcess);
        SortReport.sortCategory(choiceSortCategory);
        SortReport.sortCollection(choiceSort);
        reportSorted = SortReport.getReportSort();
        refetch();
        //clear number vote
        lessNum.clear();
        mostNum.clear();

    }


    @FXML
    public void buttonSort() throws IOException {
        //category
        choiceSortCategory = sortChoice.getValue();
        //setSort
        reportSorted = reportLists.getReportLists();
        SortReport.setReportSort(reportSorted);
        SortReport.sortProcess(choiceSortProcess);
        SortReport.sortCollection(choiceSort);
        if(!choiceSortCategory.equals("All")){
            SortReport.sortCategory(choiceSortCategory);
            reportSorted = SortReport.getReportSort();
            refetch();

        }else{
            reportSorted = SortReport.getReportSort();
            refetch();
        }

        //clear number vote
        lessNum.clear();
        mostNum.clear();
    }






    @FXML
    private void sortVote() throws IOException {
        String less = lessNum.getText();
        String most = mostNum.getText();
        reportSorted = reportLists.getReportLists();
        SortReport.setReportSort(reportSorted);
        SortReport.sortProcess(choiceSortProcess);
        SortReport.sortCollection(choiceSort);
        SortReport.sortVote(less,most);
        reportSorted = SortReport.getReportSort();
        refetch();



    }
}