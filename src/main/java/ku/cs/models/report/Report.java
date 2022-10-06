package ku.cs.models.report;

import ku.cs.models.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Report {
    private User reporter;
    private String title;
    private Category category;
    private String reportStage;
    private String problemDate;
    private int countLike;
    private List<String> userNameLike;
    private Date date;
    private String time;
    private String agency;
    private String staff;
    private String process;
    private String categoryReportPost;
    private String dateRequestDalete;
    private String timeRequestDalete;




    public Report(User reporter, String title, Category category, String reportStage,
                  String problemDate,String time,String like,String userLike,
                  String agency , String staff, String process)
    {
        this.reporter = reporter;
        this.title = title;
        this.category = category;
        this.reportStage = reportStage;
        this.problemDate = problemDate;
        this.countLike = Integer.parseInt(like);
        this.time = time;
       String[] userLink = userLike.split("\\|");
       this.userNameLike = new ArrayList<>();
        Collections.addAll(userNameLike,userLink);

        try {
            String dateString = problemDate + " " + time;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            this.date =formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.agency = agency;
        this.staff = staff;
        this.process = process;

    }

    public void setRequestDaleteReportPost(String categoryReportPost , String dateRequestDalete , String timeRequestDalete) {
        this.categoryReportPost = categoryReportPost;
        this.dateRequestDalete = dateRequestDalete;
        this.timeRequestDalete = timeRequestDalete;
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

    public List<String> getUserNameLike() {
        return userNameLike;
    }
    public void addLike(String userName){
        this.countLike = this.countLike + 1;
        userNameLike.add(userName);
    }

    public void deleteLike(String userName){
        this.countLike = this.countLike - 1;
        userNameLike.remove(userName);
    }

    public Date getDate() {
        return date;
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

    public String getCategoryReportPost() {
        return categoryReportPost;
    }

    public String getDateRequestDalete() {
        return dateRequestDalete;
    }

    public String getTimeRequestDalete() {
        return timeRequestDalete;
    }

    public void setProcessProblem(String process, String reportStage, String staff){
        this.process = process;
        this.reportStage = reportStage;
        this.staff = staff;
    }
}
