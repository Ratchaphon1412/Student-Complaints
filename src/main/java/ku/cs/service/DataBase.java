package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ku.cs.models.Account;
import ku.cs.models.admin.Admin;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.user.User;


import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class DataBase<DataObject> implements DynamicDatabase<DataObject> {

    private final String endpointPath = "database";
    private LinkedHashMap<String,LinkedHashMap<String,String>> accountList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;
    private ArrayList<LinkedHashMap<String,String>> logList;
    private DataObject account = null;

    private Admin admin;
    private Stuff stuff;
    private User user;

    public DataBase(){
        initializeData();
    }



    public void initializeData(){
        accountList = new LinkedHashMap<>();
        reportList = new LinkedHashMap<>();
        logList = new ArrayList<>();
        readFile("account.csv");
        readFile("log.csv");
    }

    public void saveToDatabase() throws IOException {
        String[] database = {"account.csv","report.csv","log.csv"};
        for(String databaseName : database){
            String path = endpointPath + File.separator + databaseName;
            File file = new File(path);
            Writer writer = new FileWriter(file);
            switch (databaseName) {
                case "account.csv" -> this.writeFile(accountList, writer);
                case "report.csv" -> this.writeFile(reportList, writer);
                case "log.csv" -> this.writeFile(logList, writer);
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
                    case "account.csv" -> accountList.put(temp.get("userName"), temp);
                    case "report.csv" -> reportList.put(temp.get("title"), temp);
                    case "log.csv" -> logList.add(temp);
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



    private void writeFile(LinkedHashMap<String,LinkedHashMap<String,String>> listOfMap, Writer writer) throws IOException {
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        List<LinkedHashMap<String,String>> temp = new ArrayList<>();
        if(listOfMap !=null && !listOfMap.isEmpty()){
            for(String mainKey : listOfMap.keySet()){
                schemaBuilder.clearColumns();
                temp.add(listOfMap.get(mainKey));
                for(String subKey:  listOfMap.get(mainKey).keySet()){
                    schemaBuilder.addColumn(subKey);
                }
            }
            schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
            System.out.println(schema.toString());
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).write(temp);
        writer.flush();
    }

    private void writeFile(ArrayList<LinkedHashMap<String,String>> listOfMap, Writer writer) throws IOException {
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
            System.out.println(schema.toString());
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).write(temp);
        writer.flush();
    }


    public String getRole(String name){
        LinkedHashMap<String,String> user = accountList.get(name);
        return user.get("role");
    }


    public DataObject login(String userName, String passWord) {
            LinkedHashMap<String,String> data =accountList.get(userName);
            switch (data.get("role")){
                case "admin" -> {
                    admin = new Admin(data.get("userName"),data.get("passWord"),data.get("pathPicture"),data.get("role"));
                    return (DataObject) admin;
                }
                case "stuff" ->{
                    stuff = new Stuff(data.get("userName"),data.get("passWord"),data.get("pathPicture"),data.get("role"));
                    return (DataObject) stuff;
                }
                case "user" ->{
                    user = new User(data.get("userName"),data.get("passWord"),data.get("pathPicture"),data.get("role"));
                    return (DataObject) user;
                }
                default -> {
                    return null;
                }
            }
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

    public boolean checkAccount(String userName){
        if(accountList.get(userName) != null){
            return true;
        }
        return false;
    }
    public String checkRole(String userName){
        return accountList.get(userName).get("role");
    }
    public boolean checkBan(String userName){
        if(accountList.get(userName).get("ban").equals("false")){
            return false;
        }
        return true;
    }

    @Override
    public boolean registerAccount(DataObject object) {
        return false;
    }

    @Override
    public boolean changeData(DataObject object) {
        return false;
    }
    public LinkedHashMap<String, LinkedHashMap<String, String>> getAccountList() {
        return accountList;
    }

    public ArrayList<LinkedHashMap<String, String>> getLogList() {
        return logList;
    }




}
