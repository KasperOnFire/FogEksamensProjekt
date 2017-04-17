package User;

public class User {
    
    int uno;
    String uname;
    String hashedPW;
    String salt;
    Float balance;

    public User(int uno, String uname, String hashedPW, String salt, Float balance) {
        this.uno = uno;
        this.uname = uname;
        this.hashedPW = hashedPW;
        this.salt = salt;
        this.balance = balance;
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

    public Float getBalance() {
        return balance;
    }
}
