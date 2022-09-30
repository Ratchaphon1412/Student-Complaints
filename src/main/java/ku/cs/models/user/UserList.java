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
    private List<User> userRequestBan;


    public  UserList(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> banList,List<LinkedHashMap<String,String>> requestban){
        userList = new ArrayList<>();
        userBanList = new ArrayList<>();
        userRequestBan = new ArrayList<>();
        createObjectUser(accountList,banList, requestban);


    }



    public void createObjectUser(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> banList,List<LinkedHashMap<String,String>> requestban){
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
        for(LinkedHashMap<String,String> account :accountList){
            if(account.get("role").equals("user")){
                boolean checkUserBan = true;
                for (User banned:userBanList){
                    if(account.get("userName").equals(banned.getUserName())){
                        checkUserBan = false;
                    }
//                    else{
//                        userList.add(banned);
//                        System.out.println(banned+"---");
//                    }
                }
                if(checkUserBan){
                    User user1 = new User(account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),false,"","","0");
                    userList.add(user1);
                }
            }
        }
        userList.addAll(userBanList);
        for(LinkedHashMap<String,String> account : accountList){
            if(account.get("role").equals("user")){
                for(LinkedHashMap<String,String> banRequest : requestban){
                    if(account.get("userName").equals(banRequest.get("userName"))){
                        User tem = new User(account.get("userName"),account.get("passWord"),account.get("pathPicture"),
                                            account.get("role"),banRequest.get("category"),banRequest.get("time"),
                                            banRequest.get("date"),null);
                        userRequestBan.add(tem);
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

    public List<User> getUserRequestBan() {
        return userRequestBan;
    }
}
