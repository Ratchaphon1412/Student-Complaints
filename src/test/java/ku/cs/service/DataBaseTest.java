package ku.cs.service;


import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.LinkedHashMap;


class DataBaseTest {

    @Test
    void testReadfile(){
        DataBase database = new DataBase();
        System.out.println(database.getAccountList());

        LinkedHashMap<String,String> test = (LinkedHashMap<String, String>) database.getAccountList().get("poomffi");
        System.out.println(test);
        System.out.println(database.getLogList());
    }

    @Test
    void testseveToFile() throws IOException {
        DataBase database = new DataBase<>();
        database.saveToDatabase();

    }

    @Test
    void login(){

    }
    @Test
    void log() throws IOException {
        DataBase dataBase = new DataBase();
        //dataBase.log("Nueng","test","/xxx/xxx/xxx/");
        System.out.println(dataBase.getLogList());
        dataBase.log("test","stuff","/xxx/xxx/xx");
        System.out.println(dataBase.getLogList());

    }

}
