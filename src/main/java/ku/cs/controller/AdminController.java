package ku.cs.controller;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import ku.cs.models.admin.Admin;
import ku.cs.service.DataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AdminController {
    @FXML
    private GridPane adminpage;
    @FXML
    private ListView<LinkedHashMap<String,String>> logListView;
    private Admin account;
    private DataBase<Admin> dataBase;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        GridPane navbar =(GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        adminpage.add(navbar,0,0);
        account = (Admin) FXRouter.getData();
        dataBase = new DataBase<>();
        showListView();
        handleSelectedListView();
    }

    @FXML
    public void testButton(ActionEvent actionEvent) {
        System.out.println(account.getUserName() + " " + account.getRole());
    }

    private void showListView() {
        logListView.getItems().addAll(dataBase.getLogList());
        logListView.refresh();
    }

    private void handleSelectedListView() {
        logListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LinkedHashMap<String,String>>() {
            @Override
            public void changed(ObservableValue<? extends LinkedHashMap<String, String>> observableValue, LinkedHashMap<String, String> oldValue, LinkedHashMap<String, String> newValue)
            {
                System.out.println(newValue + " is selected");showSelectedLog(newValue);
            }});
    }

    private void showSelectedLog(LinkedHashMap<String,String> log){
        //nameLabel.setText(log.get("userName"));
    }









}