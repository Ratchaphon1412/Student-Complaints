package ku.cs.service;

import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    public boolean signUp(String name,String password,String role, String path, File file){
        Path target;
        BufferedWriter bw = null;
        //String file = getClass().getResource("/ku/cs/database/account.csv").getPath();
        //String f ใช้สำหรับwindow เท่านั้น
        String f = System.getProperty("user.dir")+File.separator+"/src/main/resources/ku/cs/database/account.csv";
        boolean status = true;
        String line = "";
        ArrayList<String[]> bigList = new ArrayList();
        String[] listData;
        try {
            BufferedReader dataBase = new BufferedReader(new FileReader(f));
            while ((line = dataBase.readLine()) != null) {
                listData = line.split(",");
                bigList.add(listData);
            }
            File profilePictureDir = new File("image");
            if (!profilePictureDir.exists()){
                profilePictureDir.mkdirs();
            }

            String[] fileSplit = file.getName().split("\\.");
            String filename = (String) (LocalDate.now() + "-" + fileSplit[fileSplit.length - 2] + "." +
                    fileSplit[fileSplit.length - 1]);
            target = FileSystems.getDefault().getPath(
                    profilePictureDir.getAbsolutePath()+System.getProperty("file.separator")+ filename);
            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);
            //check if it has account in database it will return true
            for(int i = 0 ;i < bigList.size() ; i++){
                if((bigList.get(i)[0]).equals(name)){
                   status = false;
                }
            }
            if(status==true){
                line = '\n'+name+','+password+','+role+','+target.toString();
                bw.write(line);
                System.out.println(line);
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

    public boolean changePassword(String name, String password){
        String f = System.getProperty("user.dir")+File.separator+"/src/main/resources/ku/cs/database/account.csv";
        String content = name+','+password;
        String line = "";
        boolean status = false;
        ArrayList<String> listdata = new ArrayList<>();
        try {
            BufferedReader database = new BufferedReader(new FileReader(f));
            while ((line = database.readLine()) != null) {
                listdata.add(content);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0 ; i < listdata.size(); i++){
                bw.write(listdata.get(i));
                if ((listdata.get(i)).equals(content)) {
                    bw.write(content);
                    status = true;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return status;

    }




}
