package ku.cs.models.user;

import ku.cs.models.Account;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;

import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    private boolean ban;
    private int countAccess;
    private String requestUnban;
    private String dateBan;

    private String dateRequestBan;
    private List<Report> reportList;




    public User(String userName, String passWord, String pathPicture, String role){
        super(userName, passWord, pathPicture, role);
        this.ban = false;
    }
    public User(String userName, String passWord, String pathPicture, String role, Boolean ban ,
                String requestUnban , String dateBan , String countAccess) {
        super(userName, passWord, pathPicture, role);
        this.ban = ban;
        this.countAccess = Integer.parseInt(countAccess);
        this.requestUnban = requestUnban;
        this.dateBan = dateBan;
        this.reportList = new ArrayList<>();
    }

    public User(String userName, String passWord, String pathPicture, String role,String date){
        super(userName, passWord, pathPicture, role);
        this.dateRequestBan = date;
        this.ban = false;
    }

    public void addUserBaned(Boolean ban , String requestUnban , String dateBan ,String countAccess){
        this.ban = ban;
        this.countAccess = Integer.parseInt(countAccess);
        this.requestUnban = requestUnban;
        this.dateBan = dateBan;
    }


    public String getRequestUnban() {
        return requestUnban;
    }

    public String getDateBan() {
        return dateBan;
    }

    public boolean isBan() {
        return ban;
    }

    public int getCountAccess() {
        return countAccess;
    }


    public String getDateRequestBan() {
        return dateRequestBan;
    }


    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public void setBan() {
        if (this.ban) {
            this.ban = false;
        } else {
            this.ban = true;
        }
    }

    public void setCountAccess() {
        if (this.ban){
            this.countAccess += 1;
        }else{
            this.countAccess += 0;
        }
    }

    @Override
    public String toString() {
        return userName + ',' +passWord + ',' + pathPicture + ',' + role;
    }
}

