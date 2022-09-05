package ku.cs.models.user;

import ku.cs.service.DataBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UserList {
    private ArrayList<User> userList;
    private ArrayList<User> userBanList;

    public  UserList(){
        userList = new ArrayList<>();
    }
    public UserList(LinkedHashMap<String,User> accountList , List<LinkedHashMap<String,String>> accountBan){
        userList = new ArrayList<>();
        for(String key : accountList.keySet()){
            addNewUser(accountList.get(key));
            for(int i = 0; i <accountBan.size();i++){
                if(key.equals(accountBan.get(i).get("userName"))){
                    String[] str = accountList.get(key).toString().split(",");
                    userBanList.add(new User(str[0],str[1],str[2],str[3],"true",accountBan.get(i).get("details"),
                                        accountBan.get(i).get("date"),accountBan.get(i).get("count")));
                }
            }
        }

    }

    public void addNewUser(User user){
        userList.add(user);
    }
    public ArrayList<User> getAllUser(){
        return userList;
    }


    public ArrayList<User> getUserBanList() {
        return userBanList;
    }



}
