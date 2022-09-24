package ku.cs;
import java.io.*;
import java.util.Properties;
import java.util.prefs.Preferences;

public class State {


    public void setTempData(){
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        try  {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            //load prop
            prop.load(propsInput);
            //set in prefs initial from prop
            prefs.put("theme",prop.getProperty("theme"));
            prefs.put("font",prop.getProperty("font"));

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveThemeToConfig(String theme) throws IOException {
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        //save state
       Properties prop = new Properties();
       prop.setProperty("theme",theme);
       prop.setProperty("font",prefs.get("font",null));
        String configFilePath = "src/config.properties";
        File configFile = new File(configFilePath);
        OutputStream outputStream = new FileOutputStream(configFile);
        prop.store(outputStream,"Change Theme");
        outputStream.close();
    }

    public void saveFontToConfig(String fontName) throws IOException {
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        //save state
        Properties prop = new Properties();
        prop.setProperty("theme",prefs.get("theme",null));
        prop.setProperty("font",fontName);
        String configFilePath = "src/config.properties";
        File configFile = new File(configFilePath);
        OutputStream outputStream = new FileOutputStream(configFile);
        prop.store(outputStream,"Change Fonts");
        outputStream.close();
    }
}






