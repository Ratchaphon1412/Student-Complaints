package ku.cs.models.staff;
import ku.cs.models.Account;
public class Staff extends Account {
    private String agency;
    public Staff(String email,String userName, String passWord, String pathPicture, String role, String agency) {
        super(email,userName, passWord, pathPicture, role);
        this.agency = agency;
    }

    public void setAgency(String agency){
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "agency='" + agency + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", pathPicture='" + pathPicture + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
