package ku.cs.service;


import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;


class DataBaseTest {

    @Test
    void testReadfile(){
         DataBase database = new DataBase<>();
        System.out.println(database.getAccountList());
        LinkedHashMap<String,String> test = (LinkedHashMap<String, String>) database.getAccountList().get("poomffi");
        System.out.println(test);
    }

    @Test
    void login(){

    }

}