package ku.cs.models.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AdminList {
    private List<Admin> adminList;

    private Admin admin;


    public AdminList(List<LinkedHashMap<String,String>> accountList){
        adminList = new ArrayList<>();
        createObjectAdmin(accountList);
    }
    private void createObjectAdmin(List<LinkedHashMap<String,String>> accountList){
        for(LinkedHashMap<String,String> account :accountList ){
//            System.out.println(account.keySet());
              if(account.get("role").equals("admin")){
                  Admin admin = new Admin(account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"));
                  adminList.add(admin);
              }

        }
    }

    public Admin getAdmin(String name) {
        for(Admin check : this.adminList){
            if (check.getUserName().equals(name)){
                return check;
            }
        }
        return null;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }
}
