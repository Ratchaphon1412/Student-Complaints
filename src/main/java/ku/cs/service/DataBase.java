package ku.cs.service;
import ku.cs.models.user.User;
import ku.cs.models.stuff.Stuff;

import java.io.*;
import java.util.LinkedHashMap;
public class DataBase<DataObject> implements DynamicDatabase<DataObject> {
    private LinkedHashMap<String, LinkedHashMap<String,String>> accountList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> reportList;
    private LinkedHashMap<String, LinkedHashMap<String,String>> stuffList;

    public DataBase(){
        readFile();
    }


//    public DataObject login(String userName ,String passWord){
//
//
//    }
    private void readFile(){
        String filePath = "database" + File.separator + "account.csv";
        File file = new File(filePath);
        BufferedReader buffer = null;
        FileReader reader = null;
        try {
            String line = "";
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
                String[] data = line.split(",");
                MemberCard card = new MemberCard(data[0].trim() , data[1].trim() , Integer.parseInt(data[2].trim()));
                list.addCard(card);
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
