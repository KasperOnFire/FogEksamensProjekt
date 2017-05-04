package User;

public class AdminUser {

    int uno;
    String uname;
    String empno;
    String empname;
    String hashedPW;
    String salt;
    String userString;

    public AdminUser(int uno, String uname, String empno, String empname, String hashedPW, String salt, String userString) {
        this.uno = uno;
        this.uname = uname;
        this.empno = empno;
        this.empname = empname;
        this.hashedPW = hashedPW;
        this.salt = salt;
        this.userString = userString;
    }

    public int getUno() {
        return uno;
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
