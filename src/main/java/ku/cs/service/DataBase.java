package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.util.LinkedHashMap;
import ku.cs.models.user.User;


public class DataBase<DataObject> implements DynamicDatabase<DataObject> {
    private final String endpointPath = "database";
    private LinkedHashMap<String,LinkedHashMap<String,String>> accountList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;


    public DataBase(){
        readFile();
    }


    private void readFile(){
        String path = endpointPath + File.separator + "account.csv";
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


    private void writeFile(){

    }
    public void log (String userName){

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
}
