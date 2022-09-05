package ku.cs.models.user;

import ku.cs.models.Account;

public class User extends Account {
    private boolean ban;
    private int countAccess;
    private String requestUnban;
    private String dateBan;

    private UserList userList;



    public User(String userName, String passWord, String pathPicture, String role){
        super(userName, passWord, pathPicture, role);
    }
    public User(String userName, String passWord, String pathPicture, String role, String ban ,
                String requestUnban , String dateBan ,String countAccess) {
        super(userName, passWord, pathPicture, role);
        this.ban = Boolean.parseBoolean(ban);
        this.countAccess = Integer.parseInt(countAccess);
        this.requestUnban = requestUnban;
        this.dateBan = dateBan;
    }

    public boolean isBan() {
        return ban;
    }

    public int getCountAccess() {
        return countAccess;
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

