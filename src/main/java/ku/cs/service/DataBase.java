package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ku.cs.models.admin.Admin;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.user.User;


import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;


public class DataBase<DataObject> implements DynamicDatabase<DataObject> {

    private final String endpointPath = "database";
    private List<LinkedHashMap<String,String>> accountList;
    private List<LinkedHashMap<String,String>> reportList;
    private List<LinkedHashMap<String,String>> logList;
    private List<LinkedHashMap<String,String>> userBanList;

    private LinkedHashMap<String,User> userList;
    private LinkedHashMap<String,Stuff> stuffList;
    private LinkedHashMap<String,Admin> adminList;

    private Admin admin;
    private Stuff stuff;
    private User user;

    public DataBase(){
        initializeData();
    }



    public void initializeData(){
        accountList = new ArrayList<>();
        reportList =  new ArrayList<>();
        logList = new ArrayList<>();
        userBanList = new ArrayList<>();
        userList = new LinkedHashMap<>();
        stuffList = new LinkedHashMap<>();
        adminList = new LinkedHashMap<>();
        readFile("account.csv");
        readFile("log.csv");
        readFile("requestunban.csv");

        // initial UserList stuffList adminList
        for(LinkedHashMap<String,String> data : accountList){
            if(data.get("role")!=null) {
                switch (data.get("role")) {
                    case "admin" -> {
                        admin = new Admin(data.get("userName"), data.get("passWord"), data.get("pathPicture"), data.get("role"));
                        adminList.put(data.get("userName"), admin);
                    }
                    case "user" -> {
                        user = new User(data.get("userName"), data.get("passWord"), data.get("pathPicture"), data.get("role"));
                        userList.put(data.get("userName"), user);
                    }
                    case "stuff" -> {
                        stuff = new Stuff(data.get("userName"), data.get("passWord"), data.get("pathPicture"), data.get("role"), data.get("agency"));
                        stuffList.put(data.get("userName"), stuff);
                    }
                }
            }
        }




    }

    public void saveToDatabase() throws IOException {
        String[] database = {"account.csv","report.csv","log.csv","requestunban.csv"};
        for(String databaseName : database){
            String path = endpointPath + File.separator + databaseName;
            File file = new File(path);
            Writer writer = new FileWriter(file);
            switch (databaseName) {
                case "account.csv" -> this.writeFile(accountList, writer);
                case "report.csv" -> this.writeFile(reportList, writer);
                case "log.csv" -> this.writeFile(logList, writer);
                case "requestunban.csv" -> this.writeFile(userBanList,writer);
            }
        }
    }


    private void readFile(String fileTaget){
        String path = endpointPath + File.separator + fileTaget;
        File file = new File(path);
        BufferedReader buffer = null;
        FileReader reader = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema =CsvSchema.emptySchema().withHeader();
            MappingIterator<LinkedHashMap<String,String>> iterator = mapper.readerFor(LinkedHashMap.class).with(schema).readValues(file);

            while(iterator.hasNext()){
                LinkedHashMap<String,String> temp = iterator.next();
                switch (fileTaget) {
                    case "account.csv" -> accountList.add(temp);
                    case "report.csv" -> reportList.add(temp);
                    case "log.csv" -> logList.add(temp);
                    case "requestunban.csv" -> userBanList.add(temp);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }




    private void writeFile(List<LinkedHashMap<String,String>> listOfMap, Writer writer) throws IOException {
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        List<LinkedHashMap<String,String>> temp = new ArrayList<>();
        if(listOfMap !=null && !listOfMap.isEmpty()){
            for (LinkedHashMap<String, String> stringStringLinkedHashMap : listOfMap) {
                schemaBuilder.clearColumns();
                temp.add(stringStringLinkedHashMap);
                for (String key : listOfMap.get(0).keySet()) {
                    schemaBuilder.addColumn(key);
                }
            }

            schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).write(temp);
        writer.flush();
    }



    public DataObject login(String userName, String passWord) throws IOException {
            switch (checkRole(userName)){
                case "admin"->{
                    if(adminList.get(userName).getPassWord().equals(passWord)){
                        admin = adminList.get(userName);
                        log(admin.getUserName(),admin.getRole(),admin.getPathPicture());
                        return (DataObject) admin;
                    }
                    break;
                }case "user"->{
                    if(userList.get(userName).getPassWord().equals(passWord)){
                        user = userList.get(userName);
                        log(user.getUserName(),user.getRole(),user.getPathPicture());
                        return (DataObject) user;
                    }
                    break;
                }case "stuff"->{
                    if(stuffList.get(userName).getPassWord().equals(passWord)){
                        stuff = stuffList.get(userName);
                        log(stuff.getUserName(),stuff.getRole(),stuff.getPathPicture());
                        return (DataObject) stuff;
                    }
                }
            }
            return null;
    }

    public void log(String userName, String role, String path) throws IOException {
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        LinkedHashMap<String,String> logTemp = new LinkedHashMap<>();
        logTemp.put("userName",userName);
        logTemp.put("role" ,role);
        logTemp.put("pathPicture",path);
        logTemp.put("date",dateFormat.format(currentDate));
        logTemp.put("time",timeFormat.format(currentDate));
        if(logList == null){
            logList = new ArrayList<>();
            this.logList.add(logTemp);
            this.saveToDatabase();
        }else{
            this.logList.add(logTemp);
            this.saveToDatabase();
        }
    }


public boolean  checkAccountDuplicate(String userName){
        for (LinkedHashMap<String,String> account : accountList){
            for (String key : account.keySet()){
                if (key.equals(userName)){
                    return  true;
                }
            }
        }
        return false;
}


    public boolean checkAccount(String userName){
      for(LinkedHashMap<String,String>account :accountList){
          if(account.get("userName").equals(userName)){
              return true;
          }
      }
        return false;
    }

    public boolean checkBan(String userName){
        for (LinkedHashMap<String,String> accountBan:userBanList){
            for (String key:accountBan.keySet()){
                if(key.equals(userName)){
                    return true;
                }
            }
        }
        return false;
    }
    public String checkRole(String userName){
        for(LinkedHashMap<String,String>account :accountList){
            if(account.get("userName").equals(userName)){
                return account.get("role");
            }
        }
        return "null";
    }




    @Override
    public boolean registerAccount(DataObject object, File file) throws IOException {
        try
        {
            User newUser = (User) object;
            newUser.setPathPicture(saveImage(newUser.getPathPicture(), newUser.getUserName(),file));
            LinkedHashMap<String,String> createAccount = new LinkedHashMap<>();
            createAccount.put("userName",newUser.getUserName());
            createAccount.put("passWord",newUser.getPassWord());
            createAccount.put("role",newUser.getRole());
            createAccount.put("pathPicture",newUser.getPathPicture());
            accountList.add(createAccount);
            saveToDatabase();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    private String saveImage(String path,String name,File file){
        File desDir = new File("image/accounts");
         try {
             if(path != null && file  != null){
                 // CREATE FOLDER IF NOT EXIST
                 if(!desDir.exists()){
                     desDir.mkdirs();
                 }
                 String[] extension = path.split("\\.");
                 String filename = name+"_"+ LocalDate.now()+"_"+System.currentTimeMillis() + "."+extension[1];
                 Path target = FileSystems.getDefault().getPath(desDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
                 Files.copy(file.toPath(),target, StandardCopyOption.REPLACE_EXISTING );
                return filename;

             }
         }catch (IOException e){
             e.printStackTrace();
         }
        return null;
    }


    @Override
    public boolean changeData(DataObject object) {
        return false;
    }


    public List<LinkedHashMap<String, String>> getAccountList() {
        return accountList;
    }

    public List<LinkedHashMap<String, String>> getReportList() {
        return reportList;
    }

    public List<LinkedHashMap<String, String>> getLogList() {
        return logList;
    }

    public LinkedHashMap<String, User> getUserList() {
        return userList;
    }

    public LinkedHashMap<String, Stuff> getStuffList() {
        return stuffList;
    }

    public LinkedHashMap<String, Admin> getAdminList() {
        return adminList;
    }

    public List<LinkedHashMap<String, String>> getUserBanList() {
        return userBanList;
    }
}
