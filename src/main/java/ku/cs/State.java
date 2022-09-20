package ku.cs;
import java.io.*;
import java.util.Properties;
import java.util.prefs.Preferences;

public class State {
    private Preferences prefs;

    public void setTempData(){

        this.prefs = Preferences.userRoot().node(this.getClass().getName());

        try  {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            //load prop
            prop.load(propsInput);
            prefs.put("theme",prop.getProperty("theme"));
            prefs.put("font",prop.getProperty("font"));

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeTheme(String theme) throws IOException {
       prefs.put("theme",theme);
       Properties prop = new Properties();
       prop.setProperty("theme",theme);
        String configFilePath = "src/config.properties";
        File configFile = new File(configFilePath);
        OutputStream outputStream = new FileOutputStream(configFile);
        prop.store(outputStream,"changetheme");
        outputStream.close();
    }
    public Preferences getPrefs() {
        return prefs;
    }
}






