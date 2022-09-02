package ku.cs.models.admin;

import ku.cs.models.Account;

public class Admin extends Account {

    public Admin(String userName, String passWord, String pathPicture, String role) {
        super(userName, passWord, pathPicture, role);
    }

    public boolean banUser(Object user){
//        user.setBan(user.getBan() ? false:true);
        return true;
    }
    public Object changeStuffAgency(Object name, Object newName){
        return null;
    }



}
