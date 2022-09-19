package ku.cs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public Preferences getPrefs() {
        return prefs;
    }
}






