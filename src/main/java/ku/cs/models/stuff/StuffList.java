package ku.cs.models.stuff;

import ku.cs.models.user.User;

import java.util.ArrayList;

public class StuffList {
    private ArrayList<Stuff> stuffAgencyList;
    Stuff stuff;

    public StuffList (){
        stuffAgencyList = new ArrayList<>();
    }

    public void addNewAgency(Stuff stuff){
        stuffAgencyList.add(stuff);
    }
    public ArrayList<Stuff> getAllStuff(){
        return stuffAgencyList;
    }


}
