package ku.cs.models.admin;

import ku.cs.models.Account;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.user.User;

public class Admin extends Account {

    public Admin(String userName, String passWord, String pathPicture, String role ) {
        super(userName, passWord, pathPicture, role);
    }

    public void banUser(User user){
        user.setBan();
    }
    public void changeStuffAgency(Stuff stuff, String newAgency){
        stuff.setAgency(newAgency);
    }



}
