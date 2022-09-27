package ku.cs.models.report;

import ku.cs.models.user.User;
import ku.cs.models.user.UserList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ReportList {
    private ArrayList<Report> reportList;

    public ReportList(){
        reportList = new ArrayList<>();
//        reportToBanUser = new ArrayList<>();
    }

//    public ReportList(List<LinkedHashMap<String,String>> requestbanlist,List<User> userList){
//        reportList = new ArrayList<>();
//        reportToBanUser = new ArrayList<>();
//        for(LinkedHashMap<String,String> tem : requestbanlist){
//            User userTarget = null;
//            for(User user : userList){
//                if(user.getUserName().equals(tem.get("name"))){
//                    userTarget = user;
//                }
//            }
//            if(userTarget != null){
//                Report account = new Report(userTarget, tem.get("Title"), tem.get("date"), tem.get("time"), tem.get("post"), tem.get("image"));
//                reportToBanUser.add(account);
//            }
//
//        }
//    }

    public void addReport(Report report){
        reportList.add(report);
    }

    public ArrayList<Report> getReportList() {
        return reportList;
    }

//    public ArrayList<Report> getReportToBanUser() {
//        return reportToBanUser;
//    }
}
