package ku.cs.models.admin;

import ku.cs.models.Account;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.stuff.StuffList;
import ku.cs.models.user.User;

import java.util.ArrayList;

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

    public Stuff addNewStuff (String userName, String passWord, String pathPicture, String role, String agency) {
        Stuff stuff = new Stuff(userName, passWord, pathPicture, role, agency);
        return stuff;
    }

    public void changeAgencyNameStuffList(StuffList stuffList,String oldName, String newName){
        stuffList.changeAgencyName(oldName, newName);
    }

    public void addNewAgencyStuff(StuffList stuffList, String newAgency){
        stuffList.addNewAgency(newAgency);
    }
    public void createMainCategory(){

    }



}
