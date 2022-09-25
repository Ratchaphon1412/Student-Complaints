package ku.cs.service;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {

    @Test
    void getRequestban() {
        DataBase dataBase = new DataBase();

        for(LinkedHashMap<String,String> list : dataBase.getRequestban()){
            System.out.println(list);
        }
    }
}