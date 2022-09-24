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
    private String date;
    private String time;
    private String image;

    public Report(User reporter, String title, String category, String reportStage, String problemDate, String receiveDate, String location) {
        this.reporter = reporter;
        this.title = title;
        this.category = category;
        this.reportStage = reportStage;
        this.problemDate = problemDate;
        this.receiveDate = receiveDate;
        this.location = location;
    }
    public Report(User reporter, String title , String date ,String time , String category , String image){
        this.reporter = reporter;
        this.title = title;
        this.date = date;
        this.time = time;
        this.category = category;
        this.image = image;

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

    public User getReporter() {
        return reporter;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }
}
