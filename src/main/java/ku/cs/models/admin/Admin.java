package ku.cs.models.admin;

import ku.cs.models.Account;
import ku.cs.models.user.User;

public class Admin extends Account {

    public Admin(String userName, String passWord, String pathPicture, String role) {
        super(userName, passWord, pathPicture, role);
    }

    public void banUser(User user){
        user.setBan();
    }
    public Object changeStuffAgency(Object name, Object newName){
        return null;
    }



}
