package ku.cs.models.report;

import ku.cs.models.user.User;

public class Report {
    private User reporter;
    private String title;
    private String category;
    private String reportStage;
    private String problemDate;
    private String receiveDate;
    private String location;

    public Report(User reporter, String title, String category, String reportStage, String problemDate, String receiveDate, String location) {
        this.reporter = reporter;
        this.title = title;
        this.category = category;
        this.reportStage = reportStage;
        this.problemDate = problemDate;
        this.receiveDate = receiveDate;
        this.location = location;
    }

    //    public String getReporterName(){
//        reporter.
//    }

    public String getTitle() {
        return title;
    }

    public String getProblemDate() {
        return problemDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public String getLocation() {
        return location;
    }
}
