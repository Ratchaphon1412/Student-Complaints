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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class   ProcessData<DataObject> implements DynamicDatabase<DataObject>{
    private DataBase dataBase;

    private AdminList adminList;

    private UserList userList;

    private StaffList staffList;

    private ReportList reportList;

    private boolean checkCategory;



    public ProcessData(){
        dataBase = new DataBase();
        adminList = new AdminList(dataBase.getAccountList());
        userList = new UserList(dataBase.getAccountList(),dataBase.getUserBanList(),dataBase.getRequestban());
        staffList = new StaffList(dataBase.getAccountList(),dataBase.getAgencyList());
        reportList = new ReportList(dataBase.getReportList(),userList,dataBase.getPatternList(),dataBase.getLikePostList(),dataBase.getRequestban());
        userList.setReportUser(reportList);
    }

    @Override
    public boolean registerAccount(DataObject object, File file,String role) throws IOException {
        try
        {
            switch (role){
                case "user":{
                    User newUser = (User) object;
                    newUser.setPathPicture(dataBase.saveImage(newUser.getPathPicture(), newUser.getUserName(),file,"accounts"));
                    LinkedHashMap<String,String> createAccount = new LinkedHashMap<>();
                    createAccount.put("email",newUser.getEmail());
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

                    staff.setPathPicture(dataBase.saveImage(staff.getPathPicture(), staff.getUserName(),file,"accounts"));
                    LinkedHashMap<String,String> createAccount = new LinkedHashMap<>();
                    createAccount.put("email",staff.getEmail());
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
                               temp += staff.getEmail();
                           }else{
                               temp += "|" + staff.getEmail();
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
                    //true ?????????????????????????????????????????? ?????????
                    LinkedHashMap<String,String> temp = new LinkedHashMap<>();

                    LocalDateTime localDateTime = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    temp.put("email",user.getEmail());
                    temp.put("date",dateTimeFormatterDate.format(localDateTime));
                    temp.put("details","");
                    temp.put("count","0");

                    userBanList.add(temp);
                    for(int i = 0 ; i < requestBan.size() ; i++){
                        if(requestBan.get(i).get("headData").equals(user.getEmail())){
                            requestBan.remove(i);
                        }
                    }
                    dataBase.setRequestban(requestBan);
                    dataBase.setUserBanList(userBanList);
                    dataBase.saveToDatabase();
                }
                else{
                    // ?????? ban

                    for(int i = 0;i < userBanList.size();i++){
                        if(userBanList.get(i).get("email").equals(user.getEmail())){
                            userBanList.remove(i);
                        }
                    }
                    dataBase.setUserBanList(userBanList);
                    dataBase.saveToDatabase();
                }
            }
            case "changeAgency"->{
                Staff staff = (Staff) object;
                List<LinkedHashMap<String,String>> agencyList = dataBase.getAgencyList();
                //remove
                for(LinkedHashMap<String,String> temp : agencyList){
                    if(!temp.get("agency").equals(staff.getAgency())){
                        String[] nameList = temp.get("staffNameList").split("\\|");
                        String nameListTemp = "";
                        for(int i = 0 ; i< nameList.length ; i++){
                            if(!nameList[i].equals(staff.getEmail())){
                                if(nameListTemp.equals("")){
                                    nameListTemp += nameList[i];
                                }else{
                                    nameListTemp += "|"+nameList[i];
                                }
                            }
                        }
                        temp.put("staffNameList",nameListTemp);
                    }
                }

                //add
                for(LinkedHashMap<String,String> temp : agencyList){

                    if(temp.get("agency").equals(staff.getAgency())){
                        if(temp.get("staffNameList").equals("")){
                            temp.put("staffNameList", staff.getEmail());
                        }else{
                            String namelist = temp.get("staffNameList");
                            namelist += "|"+ staff.getEmail();
                            temp.put("staffNameList",namelist);
                        }
                    }
                }


                dataBase.setAgencyList(agencyList);
                dataBase.saveToDatabase();

            }
            case "like"-> {
                Report report = (Report) object;
                List<LinkedHashMap<String, String>> linkList = dataBase.getLikePostList();
                LinkedHashMap<String, String> newLinkLine = new LinkedHashMap<>();
                int countIndex = 0;
                for (LinkedHashMap<String, String> temp : linkList) {
                    if (temp.get("title").equals(report.getTitle())) {
                        newLinkLine.put("title", report.getTitle());
                        newLinkLine.put("like", String.valueOf(report.getCountLike()));
                        String allUserLike = "";
                        int count = 0;
                        for (String user : report.getUserNameLike()) {

                            if (count == 0) {
                                allUserLike += user;
                            } else {
                                allUserLike += "|" + user;
                            }
                            count++;
                        }
                        newLinkLine.put("email", allUserLike);
                        break;
                    }
                    countIndex++;
                }
                linkList.remove(countIndex);
                linkList.add(newLinkLine);
                dataBase.setLikePostList(linkList);
                dataBase.saveToDatabase();
            }
            case "addPrecessProblem"->{
                Report report = (Report) object;
                List<LinkedHashMap<String,String>> reportList = dataBase.getReportList();
                for(LinkedHashMap<String,String> temp : reportList){
                    if(temp.get("title").equals(report.getTitle())){
                        temp.put("process" ,report.getProcess());
                        temp.put("reportStage" , report.getReportStage());
                        temp.put("staffemail", report.getStaff());
                    }
                }
                dataBase.setReportList(reportList);
                dataBase.saveToDatabase();
            }
            case "deletePost"->{
                Report report = (Report) object;
                List<LinkedHashMap<String,String>> requestBan= dataBase.getRequestban();
                List<LinkedHashMap<String,String>> reportList = dataBase.getReportList();
                for(int i = 0 ; i < requestBan.size() ; i++){
                    if(report.getTitle().equals(requestBan.get(i).get("headData"))){
                        requestBan.remove(i);
                    }
                }
                for(int i = 0 ; i < reportList.size() ; i++){
                    if(report.getTitle().equals(reportList.get(i).get("title"))){
                        reportList.remove(i);
                    }
                }
                dataBase.setReportList(reportList);
                dataBase.setRequestban(requestBan);
                dataBase.saveToDatabase();
            }
            case "reportUser"->{
                Report report = (Report) object;
                List<LinkedHashMap<String,String>> requestBan= dataBase.getRequestban();
                LinkedHashMap<String,String> temp = new LinkedHashMap<>();

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                temp.put("headData",report.getReporter().getEmail());
                temp.put("dateTime",formattedDate);
                temp.put("type","user");
                temp.put("textReport",report.getReportPostText());
                requestBan.add(temp);
                dataBase.setRequestban(requestBan);
                dataBase.saveToDatabase();
            }
            case "reportPost"->{
                Report report = (Report) object;
                List<LinkedHashMap<String,String>> requestBan= dataBase.getRequestban();
                LinkedHashMap<String,String> temp = new LinkedHashMap<>();

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                temp.put("headData",report.getTitle());
                temp.put("dateTime",formattedDate);
                temp.put("type","post");
                temp.put("textReport",report.getReportPostText());
                requestBan.add(temp);
                dataBase.setRequestban(requestBan);
                dataBase.saveToDatabase();

            }
            case "deleteReport"->{
                Report report = (Report) object;
                List<LinkedHashMap<String,String>> requestBan= dataBase.getRequestban();
                for(int i = 0 ; i < requestBan.size() ; i++){
                    if(report.getTitle().equals(requestBan.get(i).get("headData"))){
                        requestBan.remove(i);
                    }
                }
                dataBase.setRequestban(requestBan);
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
            if(account.get("email").equals(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean checkBan(String userName){
        List<LinkedHashMap<String, String>> banList = dataBase.getUserBanList();
        for (LinkedHashMap<String, String> dataLine : banList){
            if(dataLine.get("email").equals(userName)){
                return true;
            }
        }
        return false;
    }
    public String checkRole(String userName){
        for(LinkedHashMap<String,String>account :dataBase.getAccountList()){
            if(account.get("email").equals(userName)){
                return account.get("role");
            }
        }
        return "null";

    }

    public boolean changePicture(String username, String password, String path, File file) throws IOException {
        dataBase.changePicture(username,password, dataBase.saveImage(path, username, file,"accounts"));
        return true;
    }

    public boolean addCategory(String category) throws IOException{
        checkCategory = false;
        List<LinkedHashMap<String, String>> newPatternList= dataBase.getPatternList();
        for (LinkedHashMap<String, String> dataLine : newPatternList){
            if(dataLine.get("category").equals(category)){
                checkCategory = true;
            }
        }
        if(!checkCategory){
            //add category in reportcategory.csv and pattern.csv
            List<LinkedHashMap<String,String>> patternList = dataBase.getPatternList();

            //create hashMap
            LinkedHashMap<String,String> newPattern = new LinkedHashMap<>();

            newPattern.put("category",category);

            patternList.add(newPattern);

            dataBase.setPatternList(patternList);

            dataBase.saveToDatabase();
            return true;
        }
        return false;
    }


    public void addText(String category, String text) throws IOException {
        List<LinkedHashMap<String, String>> patternList = dataBase.getPatternList();
        for (LinkedHashMap<String, String> dataLine : patternList) {
            if (dataLine.get("category").equals(category)) {
                if (dataLine.get("text").equals("")) {
                    dataLine.put("text", text);
                    dataBase.saveToDatabase();
                    //System.out.println("pp");
                } else {
                    String temp = dataLine.get("text");
                    temp += "|" + text;
                    dataLine.put("text", temp);

                    dataBase.saveToDatabase();
                    //System.out.println("oo");
                }
            }
        }
    }

    public void addImage(String category, String image) throws IOException {
        List<LinkedHashMap<String, String>> patternList = dataBase.getPatternList();
        for (LinkedHashMap<String, String> dataLine : patternList) {
            if (dataLine.get("category").equals(category)) {
                if (dataLine.get("image").equals("")) {
                    dataLine.put("image", image);
                    dataBase.saveToDatabase();
                    //System.out.println("pp");
                } else {
                    String temp = dataLine.get("image");
                    temp += "|" + image;
                    dataLine.put("image", temp);

                    dataBase.saveToDatabase();
                    //System.out.println("oo");
                }
            }
        }
    }


    public void createPost(String title ,User reporter, String category, String agency,ArrayList<String>dataText,ArrayList<File>dataImage) throws IOException {
        List<LinkedHashMap<String,String>> reportlist = dataBase.getReportList();
        LinkedHashMap<String,String> temp = new LinkedHashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatterTime =DateTimeFormatter.ofPattern("hh:mm:ss");
        DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        temp.put("title",title);
        temp.put("email",reporter.getEmail());
        temp.put("category",category);
        temp.put("reportStage","in queue");
        temp.put("problemDate",dateTimeFormatterDate.format(localDateTime));
        temp.put("time",dateTimeFormatterTime.format(localDateTime));
        int countText = 0;
        String dataTextFormatted = "";
        //text
        for(String tempText : dataText){
            if(countText == 0){
                dataTextFormatted += tempText;
            }else{
                dataTextFormatted += "|"+tempText;
            }
            countText++;
        }
        temp.put("text",dataTextFormatted);

        //save image to reports directory and return name
        int countImage = 0;
        String dataImageFormatted = "";
        for(File tempImage : dataImage){
            if(countImage == 0){
                dataImageFormatted += dataBase.saveImage(tempImage.getAbsolutePath(),title,tempImage,"reports");
            }else{
                dataImageFormatted +="|"+dataBase.saveImage(tempImage.getAbsolutePath(),title,tempImage,"reports");
            }
            countImage++;
        }
        temp.put("image",dataImageFormatted);
        temp.put("agency",agency);
        temp.put("staffemail","");
        temp.put("process","");

        reportlist.add(temp);
        dataBase.setReportList(reportlist);

        List<LinkedHashMap<String,String>> likeList = dataBase.getLikePostList();
        LinkedHashMap<String,String> tempLike = new LinkedHashMap<>();
        tempLike.put("title",title);
        tempLike.put("like","0");
        tempLike.put("email","");

        likeList.add(tempLike);
        dataBase.setLikePostList(likeList);

        dataBase.saveToDatabase();


    }



    public void  selectAgency(String category, String agency) throws IOException {
        List<LinkedHashMap<String, String>> patternList = dataBase.getPatternList();
        for (LinkedHashMap<String, String> dataLine : patternList) {
            if (dataLine.get("category").equals(category)) {
                if(dataLine.get("agency").equals("")){
                    dataLine.put("agency", agency);
                    dataBase.saveToDatabase();
                }

            }
        }
    }

    public ArrayList<String> dropDownAgency() {
        ArrayList<String> dropDownAgency = new ArrayList<>();
        List<LinkedHashMap<String, String>> agencyList = dataBase.getAgencyList();
        for (LinkedHashMap<String, String> dataLine : agencyList) {
            dropDownAgency.add(dataLine.get("agency"));
        }
        return dropDownAgency;
    }

    public void requestBan(String userName,String testRequest) throws IOException {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<LinkedHashMap<String, String>> requestUnban= dataBase.getUserBanList();
        for (LinkedHashMap<String, String> dataLine : requestUnban) {
            if(dataLine.get("email").equals(userName)){
                dataLine.put("details", testRequest);
                dataLine.put("date",dateTimeFormatterDate.format(localDateTime));
                dataBase.saveToDatabase();
            }
        }
    }

    public void refreshContAccess(String email, int count) throws IOException {
        String countAccess = Integer.toString(count);
        List<LinkedHashMap<String, String>> requestUnban= dataBase.getUserBanList();
        for (LinkedHashMap<String, String> dataLine : requestUnban) {
            if(dataLine.get("email").equals(email)){
                dataLine.put("count", countAccess);
                dataBase.saveToDatabase();
            }
        }
    }


    public AdminList getAdminList() {
        return adminList;
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
