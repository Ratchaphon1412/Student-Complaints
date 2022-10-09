package ku.cs.models.admin;

import ku.cs.models.Account;
import ku.cs.models.staff.Staff;
import ku.cs.models.staff.StaffList;
import ku.cs.models.user.User;

public class Admin extends Account {

    public Admin(String email,String userName, String passWord, String pathPicture, String role ) {
        super(email,userName, passWord, pathPicture, role);
    }

    public void banUser(User user){
        user.setBan();
    }
    public void changeStuffAgency(Staff staff, String newAgency){
        staff.setAgency(newAgency);
    }

    public Staff addNewStuff (String email,String userName, String passWord, String pathPicture, String role, String agency) {
        Staff staff = new Staff(email,userName, passWord, pathPicture, role, agency);
        return staff;
    }

    public void changeAgencyNameStuffList(StaffList staffList, String oldName, String newName){
        staffList.changeAgencyName(oldName, newName);
    }

    public void addNewAgencyStuff(StaffList staffList, String newAgency){
        staffList.addNewAgency(newAgency);
    }
    public void createMainCategory(){

    }



}
