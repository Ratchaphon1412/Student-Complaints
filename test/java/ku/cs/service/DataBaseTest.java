package ku.cs.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {


    @Test
    void getAccountList() {
        DataBase dataBase = new DataBase();
        assertEquals(null,dataBase.getAccountList());

    }
}