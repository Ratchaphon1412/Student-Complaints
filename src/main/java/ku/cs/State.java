package ku.cs;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

public class State {
    private Preferences prefs;

    public State(){
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
        try  {
            InputStream input = new FileInputStream(getClass().getResource("/ku/cs/config.properties").toExternalForm());
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}






