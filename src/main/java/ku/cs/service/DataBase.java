package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
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


    public DataBase(){
        initializeData();
    }



    public void initializeData(){
        accountList = new ArrayList<>();
        reportList =  new ArrayList<>();
        logList = new ArrayList<>();
        userBanList = new ArrayList<>();
        readFile("account.csv");
        readFile("log.csv");
        readFile("requestunban.csv");
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


    String saveImage(String path, String name, File file){
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts");
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
        System.out.println("pp");
        return false;

    }




    public void setUserBanList(List<LinkedHashMap<String, String>> userBanList) {
        this.userBanList = userBanList;
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


    public void setAccountList(List<LinkedHashMap<String, String>> accountList) {
        this.accountList = accountList;
    }
}
