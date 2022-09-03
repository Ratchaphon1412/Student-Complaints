package ku.cs.service;
import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.models.stuff.Stuff;

import java.io.*;
import java.util.LinkedHashMap;
public class DataBase<DataObject> implements DynamicDatabase<DataObject> {
    private LinkedHashMap<String, LinkedHashMap<String,String>> accountList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> stuffList;

    public DataBase(){
        readFile(accountList,"account.csv");
    }


//    public DataObject login(String userName ,String passWord){
//
//
//    }
    private void readFile(LinkedHashMap<String, LinkedHashMap<String,String>> target, String filepath){
        String filePath = "database" + File.separator + filepath;
        File file = new File(filePath);
        BufferedReader buffer = null;
        FileReader reader = null;
        try {
            String line = "";
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while ((line = buffer.readLine()) != null) {
                LinkedHashMap<String,String> linkedHashMap = null;
                String[] data = line.split("|");
                String[] value = data[1].split(",");
                for(int i = 0 ; i < value.length;i++){
                    String[] invalue = value[i].split("=");
                    linkedHashMap.put(invalue[0], invalue[1]);
                }
                System.out.println(data[0] + " " + linkedHashMap);
                target.put(data[0] , linkedHashMap);
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
