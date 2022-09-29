package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NamePostsUserController {

    @FXML
    private Label namePosts;

    private ClickNamePostsUser clickNamePostsUser;

    private String nameTitle;


    @FXML
    private void click(MouseEvent mouseEvent){
        clickNamePostsUser.clickNamePostUser(nameTitle);
    }



    public void setData(String name , ClickNamePostsUser clickNamePostsUser){
        this.clickNamePostsUser = clickNamePostsUser;
        this.nameTitle = name;
        this.namePosts.setText(name);
    }

}
