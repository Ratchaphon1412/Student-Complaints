package ku.cs.service;


import ku.cs.models.admin.Admin;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.user.User;
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
        DataBase<Stuff> database = new DataBase<>();
        if(database.getRole("poomffi").equals("admin")){
            DataBase<Admin> databasebase = new DataBase<>();
            databasebase.login("poomffi","123456");
        }
        else if(database.getRole("poomffi").equals("user")){
            DataBase<User> databasebase = new DataBase<>();
            databasebase.login("poomffi","123456");
        }
        else if(database.getRole("poomffi").equals("stuff")){
            DataBase<Stuff> databasebase = new DataBase<>();
            databasebase.login("poomffi","123456");
        }


    }
    @Test
    void log(){
        DataBase dataBase = new DataBase();
        //dataBase.log("Nueng","test","/xxx/xxx/xxx/");
        System.out.println(dataBase.getLogList());
        System.out.println(dataBase.getLogList().get(dataBase.getLogList().size()-1));
    }

    @Test
    void getRole() {
        DataBase user = new DataBase<>();
        System.out.println(user.getRole("poomffi"));

    }
}
