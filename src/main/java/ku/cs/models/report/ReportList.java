package ku.cs.models.report;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reportList;

    public ReportList(){
        reportList = new ArrayList<>();
    }

    public void addReport(Report report){
        reportList.add(report);
    }
}
