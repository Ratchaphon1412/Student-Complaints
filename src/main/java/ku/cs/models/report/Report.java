package ku.cs.models.report;

import ku.cs.models.user.User;

public class Report {
    private User reporter;
    private String title;
    private Category category;
    private String reportStage;
    private String problemDate;
    private String receiveDate;
    private String agency;
    private String staff;
    private String process;


    public Report(User reporter, String title,
                  Category category, String reportStage,
                  String problemDate, String receiveDate,
                  String agency , String staff,
                  String process) {
        this.reporter = reporter;
        this.title = title;
        this.category = category;
        this.reportStage = reportStage;
        this.problemDate = problemDate;
        this.receiveDate = receiveDate;
        this.agency = agency;
        this.staff = staff;
        this.process = process;

    }


    public Category getCategory() {
        return category;
    }

    public String getReportStage() {
        return reportStage;
    }

    public String getTitle() {
        return title;
    }

    public String getProblemDate() {
        return problemDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public User getReporter() {
        return reporter;
    }

    public String getAgency() {
        return agency;
    }

    public String getStaff() {
        return staff;
    }

    public String getProcess() {
        return process;
    }

    public void setProcessProblem(String process,String reportStage,String staff){
        this.process = process;
        this.reportStage = reportStage;
        this.staff = staff;
    }
}
