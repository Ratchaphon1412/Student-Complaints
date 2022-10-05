package ku.cs.models.staff;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StaffList {
    private LinkedHashMap<String, String> staffAgencyList;
    private ArrayList<String> agency;

    private LinkedHashMap<String,String> agencyStaffList;
    private List<Staff> staffList;

    Staff staff;


    public StaffList(List<LinkedHashMap<String,String>> accountList, List<LinkedHashMap<String,String>> agencyList){
        staffAgencyList = new LinkedHashMap<>();
        staffList = new ArrayList<>();
        agency = new ArrayList<>();
        agencyStaffList = new LinkedHashMap<>();

        createObjectStaff(accountList,agencyList);
        checkAgency(agencyList);
        setAgency(agencyList);
    }

    public void toStaffList(Staff staff){
        String name = staff.getUserName();
        String key = staff.getAgency();
        staffAgencyList.put(key, name);

    }

    private void setAgency(List<LinkedHashMap<String,String>> agecyList){
        for(int i = 0 ; i < agecyList.size() ; i++){
            agency.add(agecyList.get(i).get("agency"));
        }
    }

    private void checkAgency(List<LinkedHashMap<String,String>> agecyList){
        for(Staff data : staffList) {
            for (int i = 0; i < agecyList.size(); i++) {
                String[] staffInAgecy = agecyList.get(i).get("staffNameList").split("\\|");
                for (String nameStaff : staffInAgecy) {
                    if (nameStaff.equals(data.getUserName())){
                        data.setAgency(agecyList.get(i).get("agency"));
                    }
                }
            }
        }

    }


    private void createObjectStaff(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> agencyList){

        for(LinkedHashMap<String,String> account :accountList ){
            if(account.get("role").equals("staff")){
                staff = new Staff(account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),"");
                staffList.add(staff);
            }
        }

    }

    public void addNewAgency(String newAgency){
       agency.add(newAgency);
    }

    public Staff getStaff(String userName) {
        for(Staff user : this.staffList){
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
    public LinkedHashMap<String, String> getAllStaff(){
        return staffAgencyList;
    }


    public List<Staff> getStaffList() {
        return staffList;
    }

    public ArrayList<String> getAgency() {
        return agency;
    }
}
