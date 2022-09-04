package ku.cs.service;

import ku.cs.models.Account;

public interface DynamicDatabase<DataObject> {
    public boolean registerAccount(DataObject object);
    public boolean changeData(DataObject object);
    public Account login(String name , String passWord);

}
