package ku.cs.models.user;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;
    private User user;

    public  UserList(){
        userList = new ArrayList<>();
    }
    public void toList(User user){
        userList.add(user);
    }
    public ArrayList<User> getAllUser(){
        return userList;
    }

}
