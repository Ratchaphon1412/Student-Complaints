package ku.cs.service;

import ku.cs.models.admin.Admin;
import ku.cs.models.admin.AdminList;
import ku.cs.models.report.ReportList;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.stuff.StuffList;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class ProcessData<DataObject> implements DynamicDatabase<DataObject>{
    private DataBase dataBase;

    private AdminList adminList;

    private UserList userList;

    private StuffList stuffList;

    private ReportList reportList;



    public ProcessData(){
        dataBase = new DataBase();
        adminList = new AdminList(dataBase.getAccountList());
        userList = new UserList(dataBase.getAccountList(),dataBase.getUserBanList(),dataBase.getRequestban());
        stuffList = new StuffList(dataBase.getAccountList());
        reportList = new ReportList();
    }

    @Override
    public boolean registerAccount(DataObject object, File file) throws IOException {
        try
        {
            User newUser = (User) object;
            newUser.setPathPicture(dataBase.saveImage(newUser.getPathPicture(), newUser.getUserName(),file));
            LinkedHashMap<String,String> createAccount = new LinkedHashMap<>();
            createAccount.put("userName",newUser.getUserName());
            createAccount.put("passWord",newUser.getPassWord());
            createAccount.put("role",newUser.getRole());
            createAccount.put("pathPicture",newUser.getPathPicture());
            //get listAccount from database
            List<LinkedHashMap<String,String>> accountList = dataBase.getAccountList();
            //add new user
            accountList.add(createAccount);
            //set field account list in database
            dataBase.setAccountList(accountList);
            //savetoDatabase
            dataBase.saveToDatabase();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean changeData(DataObject object, String key) throws IOException {
        switch(key){
            case "banUser"->{
               User user = (User) object;
                List<LinkedHashMap<String,String>> userBanList= dataBase.getUserBanList();
                List<LinkedHashMap<String,String>> requestBan= dataBase.getRequestban();
                if(user.isBan()){
                    //true แสดงว่าพึ่งโดน แบน
                    LinkedHashMap<String,String> temp = new LinkedHashMap<>();
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    temp.put("userName",user.getUserName());
                    temp.put("date",dateFormat.format(currentDate));
                    temp.put("details","");
                    temp.put("count","0");

                    userBanList.add(temp);
                    for(LinkedHashMap<String,String> data : requestBan){
                        if(data.get("userName").equals(user.getUserName())){
                            data.clear();
                        }
                    }
                    dataBase.setRequestban(requestBan);
                    dataBase.setUserBanList(userBanList);
                    dataBase.saveToDatabase();
                }
                else{
                    // ลบ ban
                    for(LinkedHashMap<String,String> data : userBanList){
                        if(data.get("userName").equals(user.getUserName())){
                            data.clear();
                        }
                    }
                    dataBase.setUserBanList(userBanList);
                    dataBase.saveToDatabase();
                }
            }
        }
        return false;
    }

    @Override
    public DataObject login(String userName, String passWord) throws IOException {
        switch (checkRole(userName)){
            case "admin"->{
               Admin check = adminList.getAdmin(userName);
                if(check.getPassWord().equals(passWord)){
                    Admin admin = check;
                    dataBase.log(admin.getUserName(),admin.getRole(),admin.getPathPicture());
                    return (DataObject) admin;
                }
                break;
            }case "user"->{
                User check =  userList.getUser(userName);
                if(check.getPassWord().equals(passWord)){
                    User user = check;
                    dataBase.log(user.getUserName(),user.getRole(),user.getPathPicture());
                    return (DataObject) user;
                }
                break;
            }case "stuff"->{
                Stuff check = stuffList.getStuff(userName);
                if(check.getPassWord().equals(passWord)){
                   Stuff stuff = check;
                    dataBase.log(stuff.getUserName(),stuff.getRole(),stuff.getPathPicture());
                    return (DataObject) stuff;
                }
            }
        }
        return null;
    }

    public boolean  checkAccountDuplicate(String userName){
        for (LinkedHashMap<String,String> account : dataBase.getAccountList()){
            for (String key : account.keySet()){
                if (key.equals(userName)){
                    return  true;
                }
            }
        }
        return false;
    }


    public boolean checkAccount(String userName){
        for(LinkedHashMap<String,String>account :dataBase.getAccountList()){
            if(account.get("userName").equals(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean checkBan(String userName){
        for (LinkedHashMap<String,String> accountBan:dataBase.getUserBanList()){
            for (String key:accountBan.keySet()){
                if(key.equals(userName)){
                    return true;
                }
            }
        }
        return false;
    }
    public String checkRole(String userName){
        for(LinkedHashMap<String,String>account :dataBase.getAccountList()){
            if(account.get("userName").equals(userName)){
                return account.get("role");
            }
        }
        return "null";

    }

    public boolean ChangPicture(String username, String password, String path, File file) throws IOException {
        dataBase.changePicture(username,password, dataBase.saveImage(path, username, file));
        return true;
    }


    public DataBase getDataBase() {
        return dataBase;
    }

    public UserList getUserList() {
        return userList;
    }

    public ReportList getReportList() {
        return reportList;
    }
}
