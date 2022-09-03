package ku.cs.service;


import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
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
    void testseveToFile(){
        DataBase database = new DataBase<>();


    }

    @Test
    void login(){

    }
    @Test
    void log(){
        DataBase dataBase = new DataBase();
        //dataBase.log("Nueng","test","/xxx/xxx/xxx/");
        System.out.println(dataBase.getLogList());
        System.out.println(dataBase.getLogList().get(dataBase.getLogList().size()-1));
    }

}
