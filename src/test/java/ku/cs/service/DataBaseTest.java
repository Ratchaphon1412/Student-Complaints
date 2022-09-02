package ku.cs.service;


import org.junit.jupiter.api.Test;


class DataBaseTest {

    @Test
    void testReadfile(){
         DataBase database = new DataBase<>();
        System.out.println(database.getAccountList());
    }

}