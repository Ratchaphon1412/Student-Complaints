package ku.cs.service;
import ku.cs.models.user.User;
import ku.cs.models.stuff.Stuff;
import java.util.LinkedHashMap;
public class DataBase<DataObject> implements DynamicDatabase<DataObject> {
    private LinkedHashMap<String,String> accountList;
    private LinkedHashMap<String,String> reportList;
    private LinkedHashMap<String,String> stuffList;

    public DataBase(){
        readFile();
    }


//    public DataObject login(String userName ,String passWord){
//
//
//    }
    private void readFile(){

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
