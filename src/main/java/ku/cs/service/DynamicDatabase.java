package ku.cs.service;

import ku.cs.models.Account;

import java.io.File;
import java.io.IOException;

public interface DynamicDatabase<DataObject> {

    public boolean registerAccount(DataObject object, File file) throws IOException;

    public boolean changeData(DataObject object);
    public DataObject login(String userName,String passWord) throws IOException;

}
