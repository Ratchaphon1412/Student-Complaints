package ku.cs.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class DataBase<DataObject> implements DynamicDatabase<DataObject> {

    private final String endpointPath = "database";
    private ArrayList<LinkedHashMap<String,String>> accountList;
//
//    private LinkedHashMap<String, LinkedHashMap<String,String>> accountList = null;

    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;


    public DataBase(){
//        readFile(accountList);
        readFile();
    }


    public void readFile(){

        String path = endpointPath + File.separator + "account.csv";
        File file = new File(path);

//    public DataObject login(String userName ,String passWord){
//
//
//    }
//    private void readFile(LinkedHashMap<String, LinkedHashMap<String,String>> target){
//        LinkedHashMap<String,String> linkedHashMap = null;
//        String filePath = "database" + File.separator + "account.csv";
//        File file = new File(filePath);

        BufferedReader buffer = null;
        FileReader reader = null;
        accountList = new ArrayList<>();

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            CsvMapper mapper = new CsvMapper();
            CsvSchema schema =CsvSchema.emptySchema().withHeader();
            MappingIterator<LinkedHashMap<String,String>> iterator = mapper.reader(Map.class).with(schema).readValues(file);
            while(iterator.hasNext()){
                accountList.add(iterator.next());

//            while ((line = buffer.readLine()) != null) {
//                String[] data = line.split("|");
//                String[] value = data[1].split(",");
//                for(int i = 0 ; i < value.length;i++){
//                    String[] invalue = value[i].split("=");
//                    linkedHashMap.put(invalue[0], invalue[1]);
//                }
//                System.out.println(data[0] + " " + linkedHashMap);
//                target.put(data[0] , linkedHashMap);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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



    @Override
    public boolean registerAccount(DataObject object) {
        return false;
    }

    @Override
    public boolean changeData(DataObject object) {
        return false;
    }

    public ArrayList<LinkedHashMap<String, String>> getAccountList() {
        return accountList;
    }
}
