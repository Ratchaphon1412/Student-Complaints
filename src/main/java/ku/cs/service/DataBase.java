package ku.cs.service;

import java.io.File;
import java.util.LinkedHashMap;
public class DataBase<DataObject> implements DynamicDatabase<DataObject> {
    private String endpointPath = "database";
    private LinkedHashMap<String,String> accountList;
    private LinkedHashMap<String,String> reportList;
    private LinkedHashMap<String,String> stuffList;

    public DataBase(){
        readFile();
    }



    private void readFile(){
        String path = endpointPath + File.separator + "account.csv";


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
