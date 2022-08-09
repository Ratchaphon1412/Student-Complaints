package ku.cs.service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBase {

    public void DataBase(){}
    public String readFile(String name, String password){
        String file = getClass().getResource("/ku/cs/database/account.csv").getPath();
        String line = "";
        ArrayList<String[]> bigListAdmin = new ArrayList();
        String[] listAdmin;
        BufferedReader adminDataBase = null;
        String stage = null;
        boolean loginStage = false;
        try {
            adminDataBase = new BufferedReader(new FileReader(file));
            while ((line = adminDataBase.readLine()) != null) {
                listAdmin = line.split(",");
                bigListAdmin.add(listAdmin);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0 ; i < bigListAdmin.size(); i++){
            if(bigListAdmin.get(i)[0].equals(name) && bigListAdmin.get(i)[1].equals(password)){
                loginStage = true;
                stage = bigListAdmin.get(i)[2];
                break;
            }
        }
        return stage;
    }
    public boolean signUp(String name,String password,String role){
        BufferedWriter bw = null;
        //String file = getClass().getResource("/ku/cs/database/account.csv").getPath();
        //String f ใช้สำหรับwindow เท่านั้น
        String f = System.getProperty("user.dir")+File.separator+"/src/main/resources/ku/cs/database/account.csv";
        boolean status = false;
        String line = "";
        ArrayList<String[]> bigList = new ArrayList();
        String[] listData;
        try {
            BufferedReader dataBase = new BufferedReader(new FileReader(f));
            while ((line = dataBase.readLine()) != null) {
                listData = line.split(",");
                bigList.add(listData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);
            //check if it has account in database it will return true
            for(int i = 0 ;i < bigList.size() ; i++){
                if((bigList.get(i)[0]).equals(name)){
                   status = true;
                }
            }
            if(status==false){
                line = '\n'+name+','+password+','+role;
                bw.write(line);
                status=true;
            }
            else{
                status = false;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
        return status;

    }

    public boolean changePassword(String name, String password, String newpassword, String role){
        String f = System.getProperty("user.dir")+File.separator+"/src/main/resources/ku/cs/database/account.csv";
        String content = name + ',' + password + ',' + role;
        String newcontent = name + ','+newpassword + ',' +role;
        String line = "";
        boolean status = false;
        BufferedWriter bw = null;
        ArrayList<String> listdata = new ArrayList<>();
        try {
            BufferedReader database = new BufferedReader(new FileReader(f));
            while ((line = database.readLine()) != null) {
                listdata.add(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            for(int i = 0 ; i < listdata.size(); i++){
                if (listdata.get(i).equals(content)) {
                    bw.write(newcontent + "\n");
                    status = true;
                }else{
                    bw.write(listdata.get(i) + "\n");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
        return status;


    }

    public void log(String username,String role){
        BufferedWriter database = null;
        try{
            String f = System.getProperty("user.dir")+File.separator+"/src/main/resources/ku/cs/database/log.csv";
//            String f = getClass().getResource("/ku/cs/database/log.csv").getPath();
            System.out.println(f);
            String log = username +","+ role +","+ LocalDate.now() +","+System.currentTimeMillis()+"\n";
            database = new BufferedWriter(new FileWriter(f,true));
            database.write(log);
            if(database!=null){
                database.close();
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }





}
