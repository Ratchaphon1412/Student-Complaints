package ku.cs.models.report;

import ku.cs.models.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private User reporter;
    private String title;
    private Category category;
    private String reportStage;
    private String problemDate;

    private int countLike;

    private String[] userNameLike;

    private Date date;
    private String time;




    public Report(User reporter, String title, Category category, String reportStage, String problemDate,String time,String like,String userLike) {
        this.reporter = reporter;
        this.title = title;
        this.category = category;
        this.reportStage = reportStage;
        this.problemDate = problemDate;
        this.countLike = Integer.parseInt(like);
        this.time = time;
        this.userNameLike = userLike.split("\\|");

        try {
            String dateString = problemDate + " " + time;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            this.date =formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


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


    public User getReporter() {
        return reporter;
    }

    public int getCountLike() {
        return countLike;
    }

    public String[] getUserNameLike() {
        return userNameLike;
    }

    public Date getDate() {
        return date;
    }
}
