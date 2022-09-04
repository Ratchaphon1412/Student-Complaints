package ku.cs.models.stuff;

import ku.cs.models.user.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class StuffList {
    private LinkedHashMap<String, String> stuffAgencyList;
    private ArrayList<String> agency ;

    Stuff stuff;

    public StuffList (){
        stuffAgencyList = new LinkedHashMap<String, String>();
        agency = new ArrayList<>();
    }

    public void toStuffList(Stuff stuff){
        String name = stuff.getUserName();
        String key = stuff.getAgency();
        stuffAgencyList.put(key, name);

    }

    public void addNewAgency(String newAgency){
       agency.add(newAgency);
    }

    public void changeAgencyName(String oldAgency, String newAgency){
        for (int i = 0; i < agency.size(); i++ ){
            if(agency.get(i) == oldAgency){
                agency.remove(oldAgency);
                agency.add(newAgency);
            }
        }
    }
    public LinkedHashMap<String, String> getAllStuff(){
        return stuffAgencyList;
    }


}
