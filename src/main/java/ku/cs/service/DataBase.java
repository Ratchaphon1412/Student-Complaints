package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class DataBase<DataObject> implements DynamicDatabase<DataObject> {
    private String endpointPath = "database";
    private ArrayList<LinkedHashMap<String,String>> accountList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;


    public DataBase(){
        readFile();
    }


    public void readFile(){

        String path = endpointPath + File.separator + "account.csv";
        File file = new File(path);
        BufferedReader buffer = null;
        FileReader reader = null;
        accountList = new ArrayList<>();

        try {
            String line = "";
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema =CsvSchema.emptySchema().withHeader();
            MappingIterator<LinkedHashMap<String,String>> iterator = mapper.reader(Map.class).with(schema).readValues(file);
            while(iterator.hasNext()){
                accountList.add(iterator.next());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
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



    @Override
    public boolean registerAccount(DataObject object) {
        return false;
    }

    @Override
    public boolean changeData(DataObject object) {
        return false;
    }
}
