package ku.cs.models.report;

import ku.cs.models.user.User;
import ku.cs.models.user.UserList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ReportList {

    private List<Report> reportLists;
    private List<Report> reportListAgency;

    private Category category;


    public ReportList(List<LinkedHashMap<String,String>>reportList,UserList userList,List<LinkedHashMap<String,String>> patternList){
        this.reportLists = new ArrayList<>();
        this.reportListAgency = new ArrayList<>();
        createObjectReport(reportList,userList,patternList);
    }

    private void createObjectReport(List<LinkedHashMap<String,String>>reportList,UserList userList,List<LinkedHashMap<String,String>> patternList){

        for(LinkedHashMap<String,String> reportKey:reportList){
            for(LinkedHashMap<String,String> pattern: patternList){
                if(pattern.get("category").equals(reportKey.get("category"))){
                    Report report = new Report(userList.getUser(reportKey.get("user")),reportKey.get("title"),new Category(reportKey.get("category"),reportKey.get("text"),reportKey.get("image"),pattern.get("text"), pattern.get("image")),
                            reportKey.get("reportStage"),reportKey.get("problemDate"),reportKey.get("receiveDate"),
                            reportKey.get("agency"),reportKey.get("staff"),reportKey.get("process"));
                    reportLists.add(report);

                }
            }
        }
    }
    public List<Report> getReportListAgency(String nameAgency){
        for(Report temp : reportLists)
        {
            if(temp.getAgency().equals(nameAgency)){
                reportListAgency.add(temp);
            }
        }
        return reportListAgency;
    }


    public List<Report> getReportLists() {
        return reportLists;
    }
}
