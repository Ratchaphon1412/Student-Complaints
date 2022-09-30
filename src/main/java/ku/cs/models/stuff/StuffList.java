package ku.cs.models.stuff;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StuffList {
    private LinkedHashMap<String, String> stuffAgencyList;
    private ArrayList<String> agency;

    private LinkedHashMap<String,String> agencyStuffList;
    private List<Stuff> stuffList;

    Stuff stuff;


    public StuffList (List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> agencyList){
        stuffAgencyList = new LinkedHashMap<>();
        stuffList = new ArrayList<>();
        agency = new ArrayList<>();
        agencyStuffList = new LinkedHashMap<>();

        createObjectStuff(accountList,agencyList);
        checkAgency(agencyList);
        setAgency(agencyList);
    }

    public void toStuffList(Stuff stuff){
        String name = stuff.getUserName();
        String key = stuff.getAgency();
        stuffAgencyList.put(key, name);

    }

    private void setAgency(List<LinkedHashMap<String,String>> agecyList){
        for(int i = 0 ; i < agecyList.size() ; i++){
            agency.add(agecyList.get(i).get("agency"));
        }
    }

    private void checkAgency(List<LinkedHashMap<String,String>> agecyList){
        for(Stuff data : stuffList) {
            for (int i = 0; i < agecyList.size(); i++) {
                String[] stuffInAgecy = agecyList.get(i).get("stuffNameList").split("\\|");
                for (String nameStuff : stuffInAgecy) {
                    if (nameStuff.equals(data.getUserName())){
                        data.setAgency(agecyList.get(i).get("agency"));
                    }
                }
            }
        }

    }


    private void createObjectStuff(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> agencyList){

        for(LinkedHashMap<String,String> account :accountList ){
            if(account.get("role").equals("stuff")){
                stuff = new Stuff(account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),"");
                stuffList.add(stuff);
            }
        }

    }

    public void addNewAgency(String newAgency){
       agency.add(newAgency);
    }

    public Stuff getStuff(String userName) {
        for(Stuff user : this.stuffList){
            if(user.getUserName().equals(userName)){
                return user;
            }

        }
        return null;
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


    public List<Stuff> getStuffList() {
        return stuffList;
    }

    public ArrayList<String> getAgency() {
        return agency;
    }
}
