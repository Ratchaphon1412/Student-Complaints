package ku.cs.models.user;

import ku.cs.models.report.Filterer;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UserList {
    private List<User> userList;

    private User user;

    private List<User> userBanList;
    private List<User> userRequestBan;


    public  UserList(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> banList,List<LinkedHashMap<String,String>> requestBan){
        userList = new ArrayList<>();
        userBanList = new ArrayList<>();
        userRequestBan = new ArrayList<>();
        createObjectUser(accountList,banList, requestBan);
    }




    public void createObjectUser(List<LinkedHashMap<String,String>> accountList,List<LinkedHashMap<String,String>> banList,List<LinkedHashMap<String,String>> requestban){
        for(LinkedHashMap<String,String> account :accountList ){
            if(account.get("role").equals("user")){
                for (LinkedHashMap<String,String>ban:banList){
                    if(account.get("email").equals(ban.get("email"))){
                        User user = new User(account.get("email"),account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),true,ban.get("details"),ban.get("date"),ban.get("count"));
                        userBanList.add(user);
                    }
                }
            }
        }
        for(LinkedHashMap<String,String> account :accountList){
            if(account.get("role").equals("user")){
                boolean checkUserBan = true;
                for (User banned:userBanList){
                    if(account.get("email").equals(banned.getEmail())){
                        checkUserBan = false;
                    }
//                    else{
//                        userList.add(banned);
//                        System.out.println(banned+"---");
//                    }
                }
                if(checkUserBan){
                    User user1 = new User(account.get("email"),account.get("userName"),account.get("passWord"),account.get("pathPicture"),account.get("role"),false,"","","0");
                    userList.add(user1);
                }
            }
        }
        userList.addAll(userBanList);
        for(LinkedHashMap<String,String> account : accountList){
            if(account.get("role").equals("user")){
                for(LinkedHashMap<String,String> banRequest : requestban){
                    if(account.get("email").equals(banRequest.get("headData")) && banRequest.get("type").equals("user")){
                        User tem = new User(account.get("email"),account.get("userName"),account.get("passWord"),account.get("pathPicture"),
                                            account.get("role"),
                                            banRequest.get("dateTime"),banRequest.get("textReport"));
                        userRequestBan.add(tem);
                    }
                }
            }
        }
    }


    public void setReportUser(ReportList reportList){
            reportList.setReportSetterSort(reportList.getReportLists());

            for(User user:userList){
                ReportList tempReport = reportList.sortReport(new Filterer<Report>() {
                    @Override
                    public boolean filter(Report report) {
                        if(user.getEmail().equals(report.getReporter().getEmail())){
                            return true;
                        }
                        return false;
                    }
                });
                user.setReportList(tempReport.getReportSort());
            }
    }









    public void addNewUser(User user){
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getUser(String name) {
        for(User check : this.userList){
            if (check.getEmail().equals(name)){

                return check;
            }
        }
        return null;
    }

    public List<User> getUserBanList() {
        return userBanList;
    }

    public List<User> getUserRequestBan() {
        return userRequestBan;
    }
}
