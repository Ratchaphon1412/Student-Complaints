package ku.cs.models.Service;

import java.io.*;
import java.util.ArrayList;

public class DataBase {

    private String readFile(String name, String password){
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
    public void signUpUser(String name,String password,String role){
        BufferedWriter bw = null;
        String content = name+','+password+role+"\n";
        //String file = getClass().getResource("/ku/cs/database/account.csv").getPath();
        //String f ใช้สำหรับwindow เท่านั้น
        String f = System.getProperty("user.dir")+File.separator+"/src/main/resources/ku/cs/database";
        try {
            FileWriter fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("File written Successfully");
//        System.out.println("C:/project2/JAVA/LoginJavaFX/LoginJavafx/src/main/resources/ku/hardcodeexecutable/loginjavafx/Database/userDataBase.csv");
//        System.out.println(f);
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

    }




}
