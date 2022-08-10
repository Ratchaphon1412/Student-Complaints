package ku.cs.service;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBase {

    public void DataBase() {
    }

    public String readFile(String name, String password) {
//        String file = getClass().getResource("/ku/cs/database/account.csv").getPath();
        String fs = File.separator;
        String file = System.getProperty("user.dir") + fs + "database" + fs + "account.csv";
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
        for (int i = 0; i < bigListAdmin.size(); i++) {
            if (bigListAdmin.get(i)[0].equals(name) && bigListAdmin.get(i)[1].equals(password)) {
                loginStage = true;
                stage = bigListAdmin.get(i)[2];
                break;
            }
        }
        return stage;
    }

    public boolean signUp(String name, String password, String role, String path, File pathOldPicture) {
        Path target;
        BufferedWriter bw = null;
        //String file = getClass().getResource("/ku/cs/database/account.csv").getPath();
        //String f ใช้สำหรับwindow เท่านั้น

        String fs = File.separator;
        String file = System.getProperty("user.dir") + fs + "database" + fs + "account.csv";
        boolean status = true;

        String line = "";
        ArrayList<String[]> bigList = new ArrayList();
        String[] listData;
        try {
            BufferedReader dataBase = new BufferedReader(new FileReader(pathOldPicture));
            while ((line = dataBase.readLine()) != null) {
                listData = line.split(",");
                bigList.add(listData);
            }
            File profilePictureDir = new File("image");
            if (!profilePictureDir.exists()) {
                profilePictureDir.mkdirs();
            }

            String[] fileSplit = pathOldPicture.getName().split("\\.");
            String filename = (String) (LocalDate.now() + "-" + fileSplit[fileSplit.length - 2] + "." +
                    fileSplit[fileSplit.length - 1]);
            target = FileSystems.getDefault().getPath(
                    profilePictureDir.getAbsolutePath() + System.getProperty("file.separator") + filename);
            Files.copy(pathOldPicture.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            //check if it has account in database it will return true
            for (int i = 0; i < bigList.size(); i++) {
                if ((bigList.get(i)[0]).equals(name)) {
                    status = false;
                }
            }
            if (status == true) {
                line = name + ',' + password + ',' + role + ',' + target.toString() + "\n";
                bw.write(line);
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
        return status;

    }


    public void log(String username, String role) {
        BufferedWriter database = null;
        try {
            String fs = File.separator;
            String file = System.getProperty("user.dir") + fs + "database" + fs + "log.csv";
//            String f = getClass().getResource("/ku/cs/database/log.csv").getPath();
            System.out.println(file);
            String log = username + "," + role + "," + LocalDate.now() + "," + System.currentTimeMillis() + "\n";
            database = new BufferedWriter(new FileWriter(file, true));
            database.write(log);
            if (database != null) {
                database.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean changePassword(String name, String password, String newpassword) {
        String f = System.getProperty("user.dir") + File.separator + "database/account.csv";
        String line = "";
        boolean status = false;
        ArrayList<String[]> listData = new ArrayList<>();
        String[] listarr;
        BufferedWriter bw = null;
        try {
            BufferedReader dataBase = new BufferedReader(new FileReader(f));
            while ((line = dataBase.readLine()) != null) {
                listarr = line.split(",");
                listData.add(listarr);
            }
            FileWriter fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < listData.size(); i++) {
                if (listData.get(i)[0].equals(name) && listData.get(i)[1].equals(password)) {
                    listData.get(i)[1] = newpassword;
                    status = true;
                }
                String content = listData.get(i)[0] + ',' + listData.get(i)[1] + ',' + listData.get(i)[2] +','+listData.get(i)[3] + "\n";
                bw.write(content);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
        return status;

    }
}
