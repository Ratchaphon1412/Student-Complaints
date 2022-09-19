package ku.cs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.prefs.BackingStoreException;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    public void test() throws BackingStoreException {
    State state = new State();
    state.setTempData();
    for (String key : state.getPrefs().keys()){
        System.out.println(key);
        System.out.println(state.getPrefs().get(key,null));
    }


    }
}