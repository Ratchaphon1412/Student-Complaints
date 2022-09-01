package ku.cs.service;


import java.util.HashMap;

public class DataBase<DataObject,Key,Value> extends HashMap<Key,Value> implements DynamicDatabase<DataObject> {


    @Override
    public boolean registerAccount(DataObject object) {
        return false;
    }

    @Override
    public boolean changeData(DataObject object) {
        return false;
    }
}
