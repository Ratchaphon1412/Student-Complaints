package ku.cs.service;
import ku.cs.models.user.User;
import ku.cs.models.stuff.Stuff;
public class DataBase<DataObject> implements DynamicDatabase<DataObject> {




//    public DataObject login(){
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
