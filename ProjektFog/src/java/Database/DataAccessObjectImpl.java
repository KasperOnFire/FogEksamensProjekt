package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import User.*;
import java.io.UnsupportedEncodingException;

public class DataAccessObjectImpl implements DataAccessObject {

    private final DBConnector dbcon;
    private final Connection conn = null;
    Password pass = new Password();

    public DataAccessObjectImpl() throws Exception {
        this.dbcon = new DBConnector();
    }

    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement("SELECT * FROM user WHERE username = (?);");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int UID = rs.getInt("UID");
                String usernameRetrieved = rs.getString("username");
                String passwordRetrieved = rs.getString("password");
                String saltRetrieved = rs.getString("salt");
                String emailRetrieved = rs.getString("email");
                String userString = rs.getString("userstring");

                user = new User(UID, usernameRetrieved, passwordRetrieved, saltRetrieved, emailRetrieved, userString);
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return user;
    }

    public AdminUser getAdminByUsername(String username) throws SQLException {
        AdminUser user = null;
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement("SELECT * FROM adminuser WHERE username = (?);");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int UID = rs.getInt("UID");
                String usernameRetrieved = rs.getString("username");
                String passwordRetrieved = rs.getString("password");
                String empnoRetrieved = rs.getString("empno");
                String empnameRetrieved = rs.getString("empname");
                String saltRetrieved = rs.getString("salt");
                String userString = rs.getString("userstring");

                user = new AdminUser(UID, usernameRetrieved, empnoRetrieved, empnameRetrieved, passwordRetrieved, saltRetrieved, userString);
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return user;
    }

    public boolean createAdmin(String username, String password, String empno, String empname) throws SQLException, UnsupportedEncodingException {
        PreparedStatement stmt = null;
        try {
            String passSalt = pass.getSaltString();
            stmt = dbcon.getConnection().prepareStatement("INSERT INTO adminuser (username, empno, empname, password, salt, userstring) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, empno);
            stmt.setString(3, empname);
            stmt.setString(4, pass.get_SHA_512_SecurePassword(password, passSalt));
            stmt.setString(5, passSalt);
            stmt.setString(6, pass.getSaltString());
            stmt.executeUpdate();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public boolean createUser(String username, String password, String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement stmt = null;
        try {
            String passSalt = pass.getSaltString();
            stmt = dbcon.getConnection().prepareStatement("INSERT INTO user (username, email, password, salt, userstring) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, pass.get_SHA_512_SecurePassword(password, passSalt));
            stmt.setString(4, passSalt);
            stmt.setString(5, pass.getSaltString());
            stmt.executeUpdate();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public int getInt(String var, String table, String term, String termName) {
        String sql = "select ? from ? where ?=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, var);
            stmt.setString(2, table);
            stmt.setString(3, term);
            stmt.setString(4, termName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(var);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public double getDouble(String var, String table, String term, String termName) {
        String sql = "select ? from ? where ?=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, var);
            stmt.setString(2, table);
            stmt.setString(3, term);
            stmt.setString(4, termName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(var);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public String getString(String var, String table, String term, String termName) {
        String sql = "select ? from ? where ?=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, var);
            stmt.setString(2, table);
            stmt.setString(3, term);
            stmt.setString(4, termName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(var);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
