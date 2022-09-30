package ku.cs.models.report;

import ku.cs.models.user.User;

public class Report {
    private User reporter;
    private String title;
    private Category category;
    private String reportStage;
    private String problemDate;
    private String receiveDate;



    public Report(User reporter, String title, Category category, String reportStage, String problemDate, String receiveDate) {
        this.reporter = reporter;
        this.title = title;
        this.category = category;
        this.reportStage = reportStage;
        this.problemDate = problemDate;
        this.receiveDate = receiveDate;

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

}
