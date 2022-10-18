package ku.cs;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);//hidding titlebar
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setFullScreen(false);
        stage.setResizable(true);
        //bug
//        stage.setMinHeight(580);
//        stage.setMinWidth(1000);
        //set icon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ku/cs/assets/images/LogoIcon.png")));
        stage.setOnHidden(e -> Platform.exit());
        ApplicationController.bind(this, stage);
        //use state
        State state = new State();
        state.setTempData();

        //route config
        configRoute();


        ApplicationController.goToCenter("LoadingScreen");
    }



    private static void configRoute() {
        String pathResource = "ku/cs/views/";
        //login zone
        ApplicationController.when("Login", pathResource+"loginView.fxml",1000,580);
        ApplicationController.when("changePassword",pathResource+"changePasswordView.fxml", 600, 400);
        ApplicationController.when("Loading",pathResource+"loadSplashScreen.fxml",300,350);
        ApplicationController.when("Alert",pathResource+"alertProgress.fxml", 600,400);
        ApplicationController.when("AlertRequest","ku/cs/components/user/alertRequestUnbanView.fxml",600,400);
        ApplicationController.when("RequestUnban","ku/cs/components/user/requestUnbanView.fxml",600,400);
        ApplicationController.when("Info",pathResource+"infoView.fxml",1000,580);
        ApplicationController.when("Register",pathResource+"registerView.fxml", 1000, 580);
        ApplicationController.when("reportUserOrPost","ku/cs/components/user/reportPostAndUser.fxml",500 , 300);
        ApplicationController.when("LoadingScreen",pathResource+"loadSplashScreen.fxml",300,350);

        //Admin zone
        ApplicationController.when("Admin",pathResource+"admin/adminDashBoardView.fxml",1000,580);
        ApplicationController.when("Setting",pathResource+"admin/settingView.fxml",1000,580);
        ApplicationController.when("AdminAgency",pathResource+"admin/agencyView.fxml",1000,580);
        ApplicationController.when("AdminAgencyAdd",pathResource +"admin/addNewAgency.fxml",600,400);
        ApplicationController.when("RegisterStaff",pathResource+"admin/registerStuff.fxml",1000,580);
        ApplicationController.when("banUser",pathResource+"admin/banUserOrUnban.fxml",1000,580);
        ApplicationController.when("addCategory","ku/cs/components/admin/addCategory.fxml",600,600);
        ApplicationController.when("AdminCategory",pathResource+"admin/categoryView.fxml",1000,580);
        ApplicationController.when("addCategory","ku/cs/components/admin/addCategory.fxml",600,600);

        //User zone
        ApplicationController.when("User",pathResource+"user/userDashBoardView.fxml",1000,580);
        ApplicationController.when("DetailReport",pathResource+"user/detailProblemView.fxml",900,760);
        ApplicationController.when("Report",pathResource+"user/detailProblemView.fxml",1000,580);
        ApplicationController.when("UserPostsProfile",pathResource+"user/userPostsView.fxml",1000,580);
        ApplicationController.when("UserSetting",pathResource+"user/userSettingView.fxml",1000, 580);
        ApplicationController.when("Post",pathResource+"user/createPostView.fxml",1000,580);
        ApplicationController.when("CreatePost","ku/cs/components/user/createPost.fxml",600,760);


        //Staff zone
        ApplicationController.when("Staff",pathResource+"staff/staffProcessProblem.fxml",1000,580);
        ApplicationController.when("SettingStaff", "ku/cs/views/staff/settingStaff.fxml",1000,580);
    }


    public static void main(String[] args) {
        launch();
    }
}
