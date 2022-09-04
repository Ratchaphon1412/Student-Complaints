package ku.cs.service;

public interface DynamicDatabase<DataObject> {
    public boolean registerAccount(DataObject object);
    public boolean changeData(DataObject object);
    public DataObject login(String userName,String passWord);
}
