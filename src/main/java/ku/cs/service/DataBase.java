package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Date;
import java.text.SimpleDateFormat;


public class DataBase<DataObject> implements DynamicDatabase<DataObject> {

    private final String endpointPath = "database";
    private LinkedHashMap<String,LinkedHashMap<String,String>> accountList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;
    private ArrayList<LinkedHashMap<String,String>> logList;

    public DataBase(){

    }

    private void readFile(String fileTaget){
        String path = endpointPath + File.separator + fileTaget;
        File file = new File(path);
        BufferedReader buffer = null;
        FileReader reader = null;
        accountList = new LinkedHashMap<String,LinkedHashMap<String,String>>();
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema =CsvSchema.emptySchema().withHeader();
            MappingIterator<LinkedHashMap<String,String>> iterator = mapper.readerFor(LinkedHashMap.class).with(schema).readValues(file);
            while(iterator.hasNext()){
                LinkedHashMap<String,String> temp = iterator.next();
                accountList.put(temp.get("userName"),temp);
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


    private void writeFile() {
        BufferedWriter database = null;
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
            this.writeFile();
        }else{
            this.logList.add(logTemp);
            this.writeFile();
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
