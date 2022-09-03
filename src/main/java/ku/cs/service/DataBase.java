package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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

    public DataBase(){
        initializeData();
    }


    public void initializeData(){
        accountList = new LinkedHashMap<String,LinkedHashMap<String,String>>();
        reportList = new LinkedHashMap<String,LinkedHashMap<String, String>>();
        logList = new ArrayList<LinkedHashMap<String,String>>();
        readFile("account.csv");
        readFile("log.csv");
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
                switch (fileTaget){
                    case "account.csv":
                        accountList.put(temp.get("userName"),temp);
                        break;
                    case "report.csv":
                        reportList.put(temp.get("title"),temp);
                        break;
                    case "log.csv":
                        logList.add(temp);
                        break;
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
                temp.add(listOfMap.get(mainKey));
                for(String subKey:  listOfMap.get(mainKey).keySet()){
                    schemaBuilder.addColumn(subKey);
                }
            }
            schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).writeAll(temp);
        writer.flush();
    }

    private void writeFile(ArrayList<LinkedHashMap<String,String>> listOfMap, Writer writer) throws IOException {
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        List<LinkedHashMap<String,String>> temp = new ArrayList<>();
        if(listOfMap !=null && !listOfMap.isEmpty()){
            for(int i = 0 ; i < listOfMap.size();i++){
                for(String key : listOfMap.get(i).keySet()){
                    schemaBuilder.addColumn(key);
                }
            }
            schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).writeAll(temp);
        writer.flush();
    }


    private void saveToDatabase() throws IOException {
       String[] database = {"account.csv","report.csv","log.csv"};
        List<String> databaseList = Arrays.asList(database);

        for(String databaseName : databaseList){
            String path = endpointPath + File.separator + databaseName;
            File file = new File(path);
            FileWriter writer = new FileWriter(file);
            if(databaseName.equals("account.csv")){
                this.writeFile(accountList,writer);
            }else if(databaseName.equals("report.csv")){
                this.writeFile(reportList,writer);
            }else if(databaseName.equals("log.csv")){
                this.writeFile(logList,writer);
            }
        }

    }

    public Object login(String name, String pass){
        Object acount = null;
        LinkedHashMap<String,String> user = accountList.get(name);
        if(user.get("passWord").equals(pass)){
            if (user.get("role").equals("admin")) {
                acount = new Admin(user.get("userName"), user.get("passWord"), user.get("pathPicture"), user.get("role"));
                //userName,passWord,role,pathPicture
            } else if (user.get("role").equals("staff")) {
                acount = new Stuff();
            } else if (user.get("role").equals("user")) {
                acount = new User(user.get("userName"), user.get("passWord"), user.get("pathPicture"), user.get("role"));
            }
        }
        return acount;
    }

    public void log(String userName,String agency,String path){
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        LinkedHashMap<String,String> logTemp = new LinkedHashMap<>();
        logTemp.put("userName",userName);
        logTemp.put("agency",agency);
        logTemp.put("pathPicture",path);
        logTemp.put("date",dateFormat.format(currentDate));
        logTemp.put("time",timeFormat.format(currentDate));
        if(logList == null){
            logList = new ArrayList<LinkedHashMap<String,String>>();
            this.logList.add(logTemp);
        }else{
            this.logList.add(logTemp);
        }
    }


    public LinkedHashMap<String, LinkedHashMap<String, String>> getAccountList() {
        return accountList;
    }

    public ArrayList<LinkedHashMap<String, String>> getLogList() {
        return logList;
    }

    @Override
    public boolean registerAccount(DataObject object) {
        return false;
    }

    @Override
    public boolean changeData(DataObject object) {
        return false;
    }

}
