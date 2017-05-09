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
            stmt = dbcon.getConnection().prepareStatement("SELECT * FROM users WHERE uname = ?;");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int UID = rs.getInt("uid");
                String usernameRetrieved = rs.getString("uname");
                String passwordRetrieved = rs.getString("password");
                String saltRetrieved = rs.getString("salt");
                String emailRetrieved = rs.getString("email");
                String userString = rs.getString("userstring");
                String carportRetrieved = rs.getString("carport");

                user = new User(UID, usernameRetrieved, passwordRetrieved, saltRetrieved, emailRetrieved, userString, carportRetrieved);
                System.out.println(user.getUname());
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
                String usernameRetrieved = rs.getString("username");
                String passwordRetrieved = rs.getString("password");
                String empnoRetrieved = rs.getString("empno");
                String empnameRetrieved = rs.getString("empname");
                String saltRetrieved = rs.getString("salt");
                String userString = rs.getString("userstring");

                user = new AdminUser(usernameRetrieved, empnoRetrieved, empnameRetrieved, passwordRetrieved, saltRetrieved, userString);
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
            stmt = dbcon.getConnection().prepareStatement("INSERT INTO adminuser VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, empno);
            stmt.setString(2, username);
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
            stmt = dbcon.getConnection().prepareStatement("INSERT INTO users VALUES (default, ?, ?, ?, ?, ?, null)");
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, pass.get_SHA_512_SecurePassword(password, passSalt));
            stmt.setString(4, passSalt);
            stmt.setString(5, pass.getSaltString());
            int i = stmt.executeUpdate();
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

    public boolean updateCarport(String jsonString, String userString) throws SQLException { //NEEDS FIXING, NULL POINTER???
        String sql = "UPDATE users SET carport = ? WHERE userString = ? and uid = ?";
        PreparedStatement stmt = null;
        int UID;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setString(1, jsonString);
            stmt.setString(2, userString);
            try {
                UID = getUIDFromUserString(userString);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
            stmt.setInt(3, UID);
            stmt.executeUpdate();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public int getUIDFromUserString(String userString) {
        String sql = "SELECT UID FROM users WHERE userstring = ?";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setString(1, userString);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("uid");
            }
        } catch (Exception e) {
        }finally{
            throw new IllegalArgumentException("Userstring not found!");
        } 
    }
}
