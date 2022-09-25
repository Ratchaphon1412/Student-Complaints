package ku.cs.models.stuff;

import ku.cs.service.DataBase;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class StuffListTest {

    @Test
    void getAgencyStuffList() {
        DataBase dataBase = new DataBase();
        StuffList stuffList = new StuffList(dataBase.getAccountList(),dataBase.getStuffAgencyList());
        for (LinkedHashMap<String,String[]> temp : stuffList.getAgencyStuffList()){
            System.out.println();
        }

        System.out.println(stuffList.getAgencyStuffList());
    }
}