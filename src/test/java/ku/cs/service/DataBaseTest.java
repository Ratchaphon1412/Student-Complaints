package ku.cs.service;


import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.LinkedHashMap;


class DataBaseTest {

    @Test
    void testReadfile(){
        DataBase database = new DataBase();
        System.out.println(database.getAccountList());

        //LinkedHashMap<String,String> test = (LinkedHashMap<String, String>) database.getAccountList().get("poomffi");
        //System.out.println(test);
        System.out.println(database.getLogList());
    }

    @Test
    void testseveToFile() throws IOException {
        DataBase database = new DataBase<>();
        database.saveToDatabase();

    }

    @Test
    void login() throws IOException {

        DynamicDatabase<Admin> database = new DataBase<>();
        Admin admin = database.login("poomffi","123456");
        System.out.println(admin.getUserName()+" "+admin.getRole());
        DynamicDatabase<User> database1 = new DataBase<>();
        User user = database1.login("j1kid1412","gug1234");
        System.out.println(user.getUserName()+" "+ user.getRole());
    }
    @Test
    void log() throws IOException {
        DataBase dataBase = new DataBase();
        //dataBase.log("Nueng","test","/xxx/xxx/xxx/");
        System.out.println(dataBase.getLogList());
        dataBase.log("test","stuff","/xxx/xxx/xx");
        System.out.println(dataBase.getLogList());
        for(int i = 0; i < dataBase.getLogList().size();i++){
            System.out.println(dataBase.getLogList().get(i));
        }

    }

    @Test
    void getRole() {
        DataBase user = new DataBase<>();
        //System.out.println(user.getRole("poomffi"));

    }

    @Test
    void getUserBanList() {
        DataBase database = new DataBase<>();
        System.out.println(database.getUserBanList());
        UserList userList = new UserList(database.getUserList(),database.getUserBanList());
        System.out.println(userList.getUserBanList());

    }
}
