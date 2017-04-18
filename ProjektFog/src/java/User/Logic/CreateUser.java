package User.Logic;

import User.User;
import Database.DataAccessObjectImpl;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class CreateUser {

    DataAccessObjectImpl DAO;

    public CreateUser() throws Exception {
        this.DAO = new DataAccessObjectImpl();
    }

    public boolean insertUser(String username, String password, String email) throws SQLException, UnsupportedEncodingException {
        if(DAO.createUser(username, password, email)){
            return true;
        }
        return false;
    }

    public boolean checkIfAvaible(String username) throws UnsupportedEncodingException, SQLException {
        if (DAO.getUserByUsername(username) == null) {
            return true;
        }
        return false;
    }
    
    public User returnUser(String username) throws SQLException{
        return DAO.getUserByUsername(username);
    }

}
