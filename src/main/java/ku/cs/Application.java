package ku.cs;

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
        stage.setMinHeight(580);
        stage.setMinWidth(1000);
        ApplicationController.bind(this, stage);
//        ApplicationController.setAnimationType("fade", 800);
        State state = new State();
        state.setTempData();
        configRoute();
        ApplicationController.goTo("banUser");
    }


    private static void configRoute() {
        String pathResource = "ku/cs/views/";
        ApplicationController.when("Login", pathResource+"loginView.fxml",1000,580);
        ApplicationController.when("changePassword",pathResource+"changePasswordView.fxml", 600, 400);
        ApplicationController.when("Admin",pathResource+"adminDashBoardView.fxml",1000,580);
        ApplicationController.when("Register",pathResource+"registerView.fxml", 1000, 580);
        ApplicationController.when("User",pathResource+"userDashBoardView.fxml",1000,580);
        ApplicationController.when("Stuff",pathResource+"stuffDashBoardView.fxml",1000,580);
        ApplicationController.when("Loading",pathResource+"loadSplashScreen.fxml",300,350);
        ApplicationController.when("Alert",pathResource+"alertProgress.fxml", 600,400);
        ApplicationController.when("banUser",pathResource+"banUserOrUnban.fxml",1000,580);
        ApplicationController.when("Agency",pathResource+"agencyView.fxml",1000,580);
        ApplicationController.when("Setting",pathResource+"settingView.fxml",1000,580);
    }


    public static void main(String[] args) {
        launch();
    }
}
