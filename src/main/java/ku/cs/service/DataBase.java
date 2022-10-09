package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;


public class DataBase {

    private final String endpointPath = "database";
    private List<LinkedHashMap<String,String>> accountList;
    private List<LinkedHashMap<String,String>> reportList;
    private List<LinkedHashMap<String,String>> logList;
    private List<LinkedHashMap<String,String>> userBanList;
    private List<LinkedHashMap<String,String>> requestban;
    private List<LinkedHashMap<String,String>> agencyList;
    private List<LinkedHashMap<String,String>> patternList;
    private List<LinkedHashMap<String,String>> likePostList;

    private LinkedHashMap<String,String[]> schemaPattern;



    public DataBase(){
        setUpSchemaPattern();
        initializeData();

    }


    public void initializeData(){
        accountList = new ArrayList<>();
        reportList =  new ArrayList<>();
        logList = new ArrayList<>();
        userBanList = new ArrayList<>();
        requestban = new ArrayList<>();
        agencyList = new ArrayList<>();
        patternList = new ArrayList<>();
        likePostList = new ArrayList<>();
        readFile("account.csv",schemaPattern.get("account.csv"));
        readFile("log.csv",schemaPattern.get("log.csv"));
        readFile("requestunban.csv",schemaPattern.get("requestunban.csv"));
        readFile("requestban.csv",schemaPattern.get("requestban.csv"));
        readFile("staffAgencyList.csv",schemaPattern.get("staffAgencyList.csv"));
        readFile("pattern.csv",schemaPattern.get("pattern.csv"));
        readFile("report.csv",schemaPattern.get("report.csv"));
        readFile("likepost.csv",schemaPattern.get("likepost.csv"));

    }

    private void setUpSchemaPattern()
    {
        schemaPattern = new LinkedHashMap<>();
        String[] accountPattern = {"userName","passWord","role","pathPicture"} ;
        String[] categoryReportPattern = {"category","pattern"};
        String[] likepostPattern = {"title","like","userName"};
        String[] logPattern = {"userName","role","pathPicture","date","time"};
        String[] patternPattern = {"category","text","image","agency"};
        String[] reportPattern ={"title","user","category","reportStage","problemDate","time","text","image","agency","staff","process"};
        String[] requestBanPattern = {"headData","dateTime","type"};
        String[] requestUnbanPattern = {"userName","date","details","count"};
        String[] staffAgencyListPattern = {"agency","staffNameList"};
        schemaPattern.put("account.csv",accountPattern);
        schemaPattern.put("categoryReport.csv",categoryReportPattern);
        schemaPattern.put("likepost.csv",likepostPattern);
        schemaPattern.put("log.csv",logPattern);
        schemaPattern.put("pattern.csv",patternPattern);
        schemaPattern.put("report.csv",reportPattern);
        schemaPattern.put("requestban.csv",requestBanPattern);
        schemaPattern.put("requestunban.csv",requestUnbanPattern);
        schemaPattern.put("staffAgencyList.csv",staffAgencyListPattern);
    }

    public void saveToDatabase() throws IOException {

        String[] database = {"account.csv","report.csv","log.csv","requestunban.csv","requestban.csv","staffAgencyList.csv","pattern.csv","likepost.csv"};
        for(String databaseName : database){
            String path = endpointPath + File.separator + databaseName;
            File file = new File(path);
            Writer writer = new OutputStreamWriter(new FileOutputStream(file),StandardCharsets.UTF_8);
            switch (databaseName) {
                case "account.csv" -> this.writeFile(accountList, writer,schemaPattern.get("account.csv"));
                case "report.csv" -> this.writeFile(reportList, writer,schemaPattern.get("report.csv"));
                case "log.csv" -> this.writeFile(logList, writer,schemaPattern.get("log.csv"));
                case "requestunban.csv" -> this.writeFile(userBanList,writer,schemaPattern.get("requestunban.csv"));
                case "requestban.csv" -> this.writeFile(requestban,writer,schemaPattern.get("requestban.csv"));
                case "staffAgencyList.csv" -> this.writeFile(agencyList,writer,schemaPattern.get("staffAgencyList.csv"));
                case "pattern.csv"->this.writeFile(patternList,writer,schemaPattern.get("pattern.csv"));
                case "likepost.csv"->this.writeFile(likePostList,writer,schemaPattern.get("likepost.csv"));
            }
        }


    }



    private void readFile(String fileTaget,String[] pattern){
        String path = endpointPath + File.separator + fileTaget;
        File file = new File(path);


        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = null;
            CsvSchema.Builder schemaBuilder = CsvSchema.builder();
            for(String key:pattern){
                schemaBuilder.addColumn(key);
            }
            schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
            MappingIterator<LinkedHashMap<String,String>> iterator = mapper.readerFor(LinkedHashMap.class).with(schema).readValues(new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8)));

            while(iterator.hasNext()){
                LinkedHashMap<String,String> temp = iterator.next();
                switch (fileTaget) {
                    case "account.csv" -> accountList.add(temp);
                    case "report.csv" -> reportList.add(temp);
                    case "log.csv" -> logList.add(temp);
                    case "requestunban.csv" -> userBanList.add(temp);
                    case "requestban.csv" -> requestban.add(temp);
                    case "staffAgencyList.csv" -> agencyList.add(temp);
                    case "pattern.csv"->patternList.add(temp);
                    case "likepost.csv"->likePostList.add(temp);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private void writeFile(List<LinkedHashMap<String,String>> listOfMap, Writer writer,String[] pattern) throws IOException {
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        List<LinkedHashMap<String,String>> temp = new ArrayList<>();

        for(String key:pattern){
            schemaBuilder.addColumn(key);
        }
        schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
        if(listOfMap !=null && !listOfMap.isEmpty()){
            for (LinkedHashMap<String, String> stringStringLinkedHashMap : listOfMap) {
                temp.add(stringStringLinkedHashMap);
            }

        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).write(temp);
        writer.flush();
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
        if(role.equals("admin")){
            return ;
        }
        if(logList == null){
            logList = new ArrayList<>();
            this.logList.add(logTemp);
            this.saveToDatabase();
        }else{
            this.logList.add(logTemp);
            this.saveToDatabase();
        }
    }


    String saveImage(String path, String name, File file,String folderName){
        File desDir = new File("image"+System.getProperty("file.separator")+folderName);
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

    public boolean changePasswordUser(String username, String oldPassword, String newPassword) throws IOException {
        for (LinkedHashMap<String, String> dataLine : accountList){
            if(dataLine.get("userName").equals(username)){
                if(dataLine.get("passWord").equals(oldPassword)){
                    dataLine.replace("passWord", newPassword);
                    saveToDatabase();
                    return true;
                }
            }
        }
        //System.out.println("pp");
        return false;
    }

    public boolean changePicture(String username, String password, String newPath) throws IOException {
        for (LinkedHashMap<String, String> dataLine : accountList){
            if(dataLine.get("userName").equals(username)){
                if(dataLine.get("passWord").equals(password)){
                    dataLine.replace("pathPicture", newPath);
                    saveToDatabase();
                    return true;
                }
            }
        }
        System.out.println("pp");
        return false;

    }







    public void setUserBanList(List<LinkedHashMap<String, String>> userBanList) {
        this.userBanList = userBanList;
    }

    public void setRequestban(List<LinkedHashMap<String, String>> requestban) {
        this.requestban = requestban;
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

    public List<LinkedHashMap<String, String>> getUserBanList() {
        return userBanList;
    }

    public List<LinkedHashMap<String, String>> getRequestban() {
        return requestban;
    }

    public void setAccountList(List<LinkedHashMap<String, String>> accountList) {
        this.accountList = accountList;
    }


    public List<LinkedHashMap<String, String>> getAgencyList() {
        return agencyList;

    }

    public void setAgencyList(List<LinkedHashMap<String, String>> agencyList) {
        this.agencyList = agencyList;
    }


    public List<LinkedHashMap<String, String>> getPatternList() {
        return patternList;
    }

    public void setPatternList(List<LinkedHashMap<String, String>> patternList) {
        this.patternList = patternList;
    }

    public List<LinkedHashMap<String, String>> getLikePostList() {
        return likePostList;
    }

    public void setLikePostList(List<LinkedHashMap<String, String>> likePostList) {
        this.likePostList = likePostList;
    }
    public void setReportList(List<LinkedHashMap<String, String>> reportList) {
        this.reportList = reportList;
    }
}
