package ku.cs.service;

import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.models.stuff.Stuff;
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


    private void writeFile(){
        BufferedWriter database = null;
        try {
            String fs = File.separator;
            String file = System.getProperty("user.dir") + fs + "database" + fs + "log.csv";
//            String f = getClass().getResource("/ku/cs/database/log.csv").getPath();
            System.out.println(file);
            //String log = username + "," + role + "," + LocalDate.now() + "," + System.currentTimeMillis() + "\n";
            database = new BufferedWriter(new FileWriter(file, true));
            //database.write(log);
            if (database != null) {
                database.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object login(String name, String pass){
        return null;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getAccountList() {
        return accountList;
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
