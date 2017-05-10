package User.Logic;

import Database.DataAccessObjectImpl;
import User.AdminUser;
import User.Password;
import User.User;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFront {

    DataAccessObjectImpl DAO;
    Password pass = new Password();

    public LoginFront() throws Exception {
        this.DAO = new DataAccessObjectImpl();
    }
    
    public void saveCarport(String userString, String json) throws SQLException{
        DAO.updateCarport(json, userString);
    }

    public User returnUser(String username) throws SQLException {
        return DAO.getUserByUsername(username);
    }

    public AdminUser returnAdminUser(String username) throws SQLException {
        return DAO.getAdminByUsername(username);
    }

    public boolean passwordCheck(String username, String password) throws SQLException, UnsupportedEncodingException {
        User user = DAO.getUserByUsername(username);

        if (user != null) {
            if (user.getHashedPW().equals(pass.get_SHA_512_SecurePassword(password, user.getSalt()))) {
                return true;
            }
        }
        return false;
    }

    public boolean adminPasswordCheck(String username, String password) throws SQLException, UnsupportedEncodingException {
        //DAO.createAdmin("Admin", "Admin", "1", "Test Nejsen");
        AdminUser user = DAO.getAdminByUsername(username);

        if (user != null) {
            if (user.getHashedPW().equals(pass.get_SHA_512_SecurePassword(password, user.getSalt()))) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList getOrders(){
        return DAO.getOrders();
    }
    
}
