package ku.cs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {


    @Test
    public void test() throws BackingStoreException {


        Preferences pref =  Preferences.userRoot().node(State.class.getName());
        System.out.println(pref.get("theme",null));
//    for (String key : state.getPrefs().keys()){
//        System.out.println(key);
//        System.out.println(state.getPrefs().get(key,null));
//    }


    }
}