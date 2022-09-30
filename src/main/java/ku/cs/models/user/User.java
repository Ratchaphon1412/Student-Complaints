package ku.cs.models.user;

import ku.cs.models.Account;
import ku.cs.models.report.Report;

public class User extends Account {
    private boolean ban;
    private int countAccess;
    private String requestUnban;
    private String dateBan;

    private Report post;
    private String time;
    private String dateRequestBan;
    private String category;





    public User(String userName, String passWord, String pathPicture, String role){
        super(userName, passWord, pathPicture, role);
        this.ban = false;
    }
    public User(String userName, String passWord, String pathPicture, String role, Boolean ban ,
                String requestUnban , String dateBan ,String countAccess) {
        super(userName, passWord, pathPicture, role);
        this.ban = ban;
        this.countAccess = Integer.parseInt(countAccess);
        this.requestUnban = requestUnban;
        this.dateBan = dateBan;
    }
    public User(String userName, String passWord, String pathPicture, String role,String category,String time,String date,Report post){
        super(userName, passWord, pathPicture, role);
        this.category = category;
        this.time = time;
        this.dateRequestBan = date;
        this.post = post;
        this.ban = false;
    }

    public void addRequestedBan(String category,String time,String date,Report post){
        this.category = category;
        this.time = time;
        this.dateRequestBan = date;
        this.post = post;
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

    public Report getPost() {
        return post;
    }

    public String getTime() {
        return time;
    }

    public String getDateRequestBan() {
        return dateRequestBan;
    }

    public String getCategory() {
        return category;
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

