package ku.cs.service;

interface DynamicDatabase<T> {
    boolean registerAccount(T object);
    boolean changeData(T object);
}
