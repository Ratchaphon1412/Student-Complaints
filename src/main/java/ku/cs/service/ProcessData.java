package ku.cs.service;

import ku.cs.models.admin.Admin;
import ku.cs.models.admin.AdminList;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import ku.cs.models.staff.Staff;
import ku.cs.models.staff.StaffList;
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

    private StaffList staffList;

    private ReportList reportList;



    public ProcessData(){
        dataBase = new DataBase();
        adminList = new AdminList(dataBase.getAccountList());
        userList = new UserList(dataBase.getAccountList(),dataBase.getUserBanList(),dataBase.getRequestban());
        staffList = new StaffList(dataBase.getAccountList(),dataBase.getAgencyList());
        reportList = new ReportList(dataBase.getReportList(),userList,dataBase.getPatternList());
    }

    @Override
    public boolean registerAccount(DataObject object, File file,String role) throws IOException {
        try
        {
            switch (role){
                case "user":{
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
                }
                case "staff":{
                    Staff staff = (Staff) object;
                    staff.setPathPicture(dataBase.saveImage(staff.getPathPicture(), staff.getUserName(),file));
                    LinkedHashMap<String,String> createAccount = new LinkedHashMap<>();
                    createAccount.put("userName", staff.getUserName());
                    createAccount.put("passWord", staff.getPassWord());
                    createAccount.put("role", staff.getRole());
                    createAccount.put("pathPicture", staff.getPathPicture());
                    //get listAccount from database
                    List<LinkedHashMap<String,String>> accountList = dataBase.getAccountList();
                    //add new stuff
                    accountList.add(createAccount);
                    dataBase.setAccountList(accountList);

                    //get list Agency
                    List<LinkedHashMap<String,String>> agencyList = dataBase.getAgencyList();
                    //loop check agency and add stuff name to agency
                    for(LinkedHashMap<String,String> agency : agencyList){
                        if(agency.get("agency").equals(staff.getAgency())){
                           String temp = agency.get("staffNameList");
                           if(temp.equals("")){
                               temp += staff.getUserName();
                           }else{
                               temp += "|" + staff.getUserName();
                           }
                           agency.put("staffNameList",temp);
                        }
                    }
                    dataBase.setAgencyList(agencyList);
                    //savetoDatabase
                    dataBase.saveToDatabase();
                    return true;

                }
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return false;
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
                    for(int i = 0 ; i < requestBan.size() ; i++){
                        if(requestBan.get(i).get("userName").equals(user.getUserName())){
                            requestBan.remove(i);
                        }
                    }
                    if(requestBan.size() == 0){
                        temp = new LinkedHashMap<>();
                        temp.put("userName","");
                        temp.put("date","");
                        temp.put("time","");
                        temp.put("category","");
                        temp.put("post","");
                        requestBan.add(temp);
                    }
                    if(requestBan.get(0).get("userName").equals("") && requestBan.size() == 2){
                        requestBan.remove(0);
                    }
                    dataBase.setRequestban(requestBan);
                    dataBase.setUserBanList(userBanList);
                    dataBase.saveToDatabase();
                }
                else{
                    // ลบ ban

                    for(int i = 0;i < userBanList.size();i++){
                        if(userBanList.get(i).get("userName").equals(user.getUserName())){
                            userBanList.remove(i);
                        }
                    }
                    if(userBanList.size() == 0){
                        LinkedHashMap<String,String> temp = new LinkedHashMap<>();
                        temp.put("userName","");
                        temp.put("date","");
                        temp.put("details","");
                        temp.put("count","");
                        userBanList.add(temp);
                    }
                    if(userBanList.get(0).get("userName").equals("") && requestBan.size() == 2){
                        userBanList.remove(0);
                    }
                    dataBase.setUserBanList(userBanList);
                    dataBase.saveToDatabase();
                }
            }
            case "changeAgency"->{
                Staff staff = (Staff) object;
                List<LinkedHashMap<String,String>> agencyList = dataBase.getAgencyList();
                //add
                for(LinkedHashMap<String,String> temp : agencyList){

                    if(temp.get("agency").equals(staff.getAgency())){
                        if(temp.get("staffNameList").equals("")){
                            temp.put("staffNameList", staff.getUserName());
                        }else{
                            String namelist = temp.get("staffNameList");
                            namelist += "|"+ staff.getUserName();
                            temp.put("staffNameList",namelist);
                        }
                    }
                }

                //remove
                for(LinkedHashMap<String,String> temp : agencyList){
                    if(!temp.get("agency").equals(staff.getAgency())){
                        String[] nameList = temp.get("staffNameList").split("\\|");
                        String nameListTemp = "";
                       for(int i = 0 ; i< nameList.length ; i++){
                           if(nameList[i].equals(staff.getUserName())){

                           }else{
                               if(i == 0){
                                   nameListTemp += nameList[i];
                               }else{
                                   nameListTemp += "|"+nameList[i];
                               }
                           }
                       }
                       temp.put("staffNameList",nameListTemp);
                    }
                }
                dataBase.setAgencyList(agencyList);
                dataBase.saveToDatabase();

            }
            case "addPrecessProblem"->{
                Report report = (Report) object;
                List<LinkedHashMap<String,String>> reportList = dataBase.getReportList();
                for(LinkedHashMap<String,String> temp : reportList){
                    if(temp.get("title").equals(report.getTitle())){
                        temp.put("process" ,report.getProcess());
                        temp.put("reportStage" , report.getReportStage());
                        temp.put("staff", report.getStaff());
                    }
                }
                dataBase.setReportList(reportList);
                dataBase.saveToDatabase();
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
            }case "staff"->{
                Staff check = staffList.getStaff(userName);
                if(check.getPassWord().equals(passWord)){
                   Staff staff = check;
                    dataBase.log(staff.getUserName(), staff.getRole(), staff.getPathPicture());
                    return (DataObject) staff;
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

    public boolean changePicture(String username, String password, String path, File file) throws IOException {
        dataBase.changePicture(username,password, dataBase.saveImage(path, username, file));
        return true;
    }

    public void addCategory(String category) throws IOException{

        List<LinkedHashMap<String,String>> categoryList = dataBase.getCategoryList();
        //create hashMap

        LinkedHashMap<String,String> newCategory = new LinkedHashMap<>();
        newCategory.put("category",category);


        categoryList.add(newCategory);

        dataBase.setCategoryList(categoryList);

        dataBase.saveToDatabase();

    }

    public void  addTitle(String category, String title) throws IOException {
        List<LinkedHashMap<String, String>> categoryList = dataBase.getCategoryList();
        for (LinkedHashMap<String, String> dataLine : categoryList){
            if(dataLine.get("category").equals(category)){
                if (dataLine.get("title").equals("")){
                    dataLine.put("title", title);
                    dataBase.saveToDatabase();
                   // System.out.println("pp");
                }else {
                    String temp = dataLine.get("title");
                    temp += "|"+title;
                    dataLine.put("title",temp);
                    dataBase.saveToDatabase();
                   // System.out.println("oo");
                }

            }
        }


    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public UserList getUserList() {
        return userList;
    }

    public StaffList getStaffList() {
        return staffList;
    }

    public ReportList getReportList() {
        return reportList;
    }
}
