package ku.cs.models.report;

import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getMapPatternValue() {
        ProcessData processData = new ProcessData();

        DataBase dataBase = new DataBase();
        List<LinkedHashMap<String,String>> reportList = dataBase.getReportList();
        System.out.println("test");
        for (LinkedHashMap<String,String> reportData : reportList){
            System.out.println(reportData.keySet());
        }



    }

    @Test
    void testGetMapPatternValue() {

        ProcessData processData = new ProcessData();

        DataBase dataBase = new DataBase();
        List<LinkedHashMap<String,String>>temp1 = dataBase.getPatternList();
        for(LinkedHashMap<String,String>x : temp1) {
            System.out.println(x.keySet());
        }
        //[category, pattern] in list


    }
}