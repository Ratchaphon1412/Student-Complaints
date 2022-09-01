package ku.cs.models;

abstract class Account {
    protected String userName;
    protected  String passWord;
    protected  String pathPicture;
    protected String role;

    public Account(String userName, String passWord, String pathPicture, String role) {
        this.userName = userName;
        this.passWord = passWord;
        this.pathPicture = pathPicture;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public String getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
