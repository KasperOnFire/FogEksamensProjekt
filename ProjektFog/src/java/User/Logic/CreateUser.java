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
        return DAO.createUser(username, password, email);
    }

    public boolean checkIfAvaible(String username) throws UnsupportedEncodingException, SQLException {
        return DAO.getUserByUsername(username) == null;
    }

    public User returnUser(String username) throws SQLException {
        return DAO.getUserByUsername(username);
    }

}
