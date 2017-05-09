package User;

public class User {

    private int uno;
    private String uname;
    private String hashedPW;
    private String salt;
    private String email;
    private String userString;
    private String carport;

    public User(int uno, String uname, String hashedPW, String salt, String email, String uString) {
        this.uno = uno;
        this.uname = uname;
        this.hashedPW = hashedPW;
        this.salt = salt;
        this.email = email;
        this.userString = uString;
    }

    public int getUno() {
        return uno;
    }

    public String getUname() {
        return uname;
    }

    public String getHashedPW() {
        return hashedPW;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public String getUserString() {
        return userString;
    }

    public String getCarport() {
        return carport;
    }

    public void setCarport(String carport) {
        this.carport = carport;
    }

}
