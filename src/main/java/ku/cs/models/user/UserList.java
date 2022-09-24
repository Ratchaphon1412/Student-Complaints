package ku.cs.models.user;

import ku.cs.controller.ListViewUserBanList;
import ku.cs.models.admin.Admin;
import ku.cs.service.DataBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UserList {
    private List<User> userList;

    private User user;

    private List<User> userBanList;

    public  UserList(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> banList){
        userList = new ArrayList<>();
        userBanList = new ArrayList<>();
        createObjectUser(accountList,banList);
        //initial banUser
        for (User user : userList){
            if(user.isBan()== true){
                userBanList.add(user);
            }
        }

    }



    public void createObjectUser(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> banList){
        for(LinkedHashMap<String,String> account :accountList ){
            if(account.get("role").equals("user")){
                for (LinkedHashMap<String,String>ban:banList){
                    if(account.get("userName").equals(ban.get("userName"))){
                        User user = new User(account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),true,ban.get("details"),ban.get("date"),ban.get("count"));
                        userBanList.add(user);
                    }
                }
            }
        }
        for(LinkedHashMap<String,String> account :accountList ){
            if(account.get("role").equals("user")){
                for (User banned:userBanList){
                    if(!account.get("userName").equals(banned.getUserName())){
                        User user1 = new User(account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),false,"","","0");
                        userList.add(user1);
                    }else{
                        userList.add(banned);
                    }
                }
            }
        }

    }

    public void addNewUser(User user){
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getUser(String name) {
        for(User check : this.userList){
            if (check.getUserName().equals(name)){
                return check;
            }
        }
        return null;
    }

    public List<User> getUserBanList() {
        return userBanList;
    }




}
