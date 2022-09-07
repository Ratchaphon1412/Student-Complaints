package ku.cs.models.user;

import ku.cs.service.DataBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UserList {
    private ArrayList<User> userList;
    private ArrayList<User> userBanList;

    public  UserList(){
        userList = new ArrayList<>();
    }

    public void addNewUser(User user){
        userList.add(user);
    }
    public ArrayList<User> getAllUser(){
        return userList;
    }


    public ArrayList<User> getUserBanList() {
        return userBanList;
    }



}
