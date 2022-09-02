package ku.cs.service;

public interface DynamicDatabase<DataObject> {
    public boolean registerAccount(DataObject object);
    public boolean changeData(DataObject object);
}
