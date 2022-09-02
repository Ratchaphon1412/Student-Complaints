package ku.cs.models.user;

import java.util.ArrayList;

public class UserList extends User {
    private ArrayList<String[]> userList;
    private User user;

    public UserList(String userName, String passWord, String pathPicture, String role, String requestUnban, ArrayList<String[]> userList, User user) {
        super(userName, passWord, pathPicture, role, requestUnban);
        this.userList = userList;
        this.user = user;
    }

}
