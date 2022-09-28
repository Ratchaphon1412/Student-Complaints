package ku.cs.service;

import org.junit.jupiter.api.Test;


import java.io.IOException;
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

    @Test
    void getStuffAgencyList() {
        DataBase dataBase = new DataBase();

//        System.out.println(dataBase.getStuffAgencyList());
//        List<LinkedHashMap<String,String>> agencyList = dataBase.getStuffAgencyList();
        //loop check agency and add stuff name to agency

//        for(LinkedHashMap<String,String> agency : agencyList){
//            if(agency.get("agency").equals("xxxxxxxx")){
//                String temp = agency.get("stuffNameList");
//                if(temp.equals("")){
//                    temp +="test";
//                }else{
//                    temp += "|" + "test";
//                }
//                agency.put("stuffNameList",temp);
//            }
//        }
//        for(LinkedHashMap<String,String> test : agencyList){
//            String[] temp= test.get("stuffNameList").split("\\|");
//            for(int i = 0;i<temp.length ;i++){
//                System.out.print(temp[i]+" ");
//            }
//
//        }
//
//        System.out.println(agencyList);

    }

    @Test
    void getAgencyList() {
        DataBase dataBase = new DataBase();
        System.out.println(dataBase.getAgencyList());
    }

    @Test
    void addNewCategory () throws IOException {
       ProcessData data = new ProcessData();
       data.addTitle("water", "xxx");
    }
}