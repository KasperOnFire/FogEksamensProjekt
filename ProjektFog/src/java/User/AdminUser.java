package User;

public class AdminUser {

    String uname;
    String empno;
    String empname;
    String hashedPW;
    String salt;
    String userString;

    public AdminUser(String uname, String empno, String empname, String hashedPW, String salt, String userString) {
        this.uname = uname;
        this.empno = empno;
        this.empname = empname;
        this.hashedPW = hashedPW;
        this.salt = salt;
        this.userString = userString;
    }

    public String getUname() {
        return uname;
    }

    public String getEmpno() {
        return empno;
    }

    public String getEmpname() {
        return empname;
    }

    public String getHashedPW() {
        return hashedPW;
    }

    public String getSalt() {
        return salt;
    }

    public String getUserString() {
        return userString;
    }
}
