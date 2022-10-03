package ku.cs.models.report;

import ku.cs.models.user.User;
import ku.cs.models.user.UserList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ReportList {

    private List<Report> reportLists;
    private List<Report> reportListAgency;

    private List<Report> reportSort;

    private List<Report> reportSetterSort;



    public ReportList(List<LinkedHashMap<String,String>>reportList,UserList userList,List<LinkedHashMap<String,String>> patternList,List<LinkedHashMap<String,String>> likePostList){
        this.reportLists = new ArrayList<>();
        this.reportSort = new ArrayList<>();
        this.reportSetterSort = new ArrayList<>();
        createObjectReport(reportList,userList,patternList,likePostList);
    }

    public ReportList(){
        this.reportSort = new ArrayList<>();
        this.reportListAgency = new ArrayList<>();
    }

    private void createObjectReport(List<LinkedHashMap<String,String>>reportList,UserList userList,List<LinkedHashMap<String,String>> patternList,List<LinkedHashMap<String,String>>likePostList){

        for(LinkedHashMap<String,String> reportKey:reportList){
            for(LinkedHashMap<String,String> pattern: patternList){
                if(pattern.get("category").equals(reportKey.get("category"))){
                  for(LinkedHashMap<String,String> like: likePostList){
                     if(like.get("title").equals(reportKey.get("title"))){
                         Report report = new Report(userList.getUser(reportKey.get("user")),reportKey.get("title"),new Category(reportKey.get("category"),reportKey.get("text"),reportKey.get("image")
                                 ,pattern.get("text"), pattern.get("image")),reportKey.get("reportStage"),
                                 reportKey.get("problemDate"),reportKey.get("time"),like.get("like"),like.get("userName"),
                                 reportKey.get("agency"),reportKey.get("staff"),reportKey.get("process"));
                         reportLists.add(report);
                     }
                  }
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

    public ReportList sortReport(Filterer<Report> filterer){
        ReportList filtered = new ReportList();
        for(Report report: reportSetterSort){
            if(filterer.filter(report)){
                filtered.addSort(report);
            }
        }

        return  filtered;
    }


    public void addSort(Report report){
        reportSort.add(report);
    }

    public List<Report> getReportSort() {
        return reportSort;
    }

    public void setReportSetterSort(List<Report> reportSetterSort) {
        this.reportSetterSort = reportSetterSort;
    }
}
