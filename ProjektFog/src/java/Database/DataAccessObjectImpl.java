package Database;

import MaterialList.Material;
import User.AdminUser;
import User.Password;
import User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import User.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Implentation of DataBaseObject. Handles all methods that has direct contact
 * with the database
 *
 * @author Kasper
 */
public class DataAccessObjectImpl implements DataAccessObject {

    private final DBConnector dbcon;
    private final Connection conn = null;
    Password pass = new Password();

    /**
     * established connection to the database when instantiated.
     *
     * @throws Exception if anything goes wrong - see DBConnector object for
     * details.
     */
    public DataAccessObjectImpl() throws Exception {
        this.dbcon = new DBConnector();
    }

    /**
     *
     * Finds all data about a user from the database. Returns null if user
     * doesn't exist.
     *
     * @param username to get data about.
     * @return User object containing found data.
     * @throws SQLException if the sqlstatement is wrong or, the access to the
     * database is wrong.
     */
    @Override
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

    /**
     *
     * Finds all data about an administrator user and returns it in an Adminuser
     * object.
     *
     * @param username to find data about
     * @return AdminUser object with the data.
     * @throws SQLException if the sqlstatement is wrong or, the access to the
     * database is wrong.
     */
    @Override
    public AdminUser getAdminByUsername(String username) throws SQLException {
        AdminUser user = null;
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement("SELECT * FROM adminuser WHERE username = ?");
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

    @Override
    public int getEmpNoByUsername(String empName) throws SQLException {
        int empNo = -1;
        PreparedStatement stmt = null;
        String sql = "SELECT empno FROM adminuser WHERE username = ?";
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setString(1, empName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empNo = rs.getInt("empno");
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return empNo;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     *
     * SQL statement for inserting a new administrator.
     *
     * @param username for the admin
     * @param password the sha-512 hashed secure password-string
     * @param empno employee number
     * @param empname employee name
     * @return Boolean determining the state of success of the insertion.
     * @throws SQLException if the sqlstatement is wrong or, the access to the
     * database is wrong.
     * @throws UnsupportedEncodingException if the hashing of password goes
     * wrong
     */
    @Override
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

    /**
     *
     * Creates a new user and saves it to the database
     *
     * @param username of the user
     * @param password sha-512 hashed secure password to save
     * @param email of the user - for later usage, e.g marketing and resetting a
     * password.
     * @return Boolean determining the state of success of the insertion.
     * @throws SQLException if the sqlstatement is wrong or, the access to the
     * database is wrong.
     * @throws UnsupportedEncodingException if the hashing of password goes
     * wrong
     */
    @Override
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

    /**
     *
     * Updates the carport a user has saved to their account
     *
     * @param jsonString of the carport
     * @param userString of the user
     * @return Boolean determining the state of success of the insertion.
     * @throws SQLException if the sqlstatement is wrong or, the access to the
     * database is wrong.
     */
    @Override
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

    @Override
    public boolean updateEMPonOrder(String empName, int ono) {
        String sql = "UPDATE orders SET empno = ? WHERE ono = ?";
        PreparedStatement stmt = null;
        int empNo;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            empNo = this.getEmpNoByUsername(empName);
            stmt.setInt(1, empNo);
            stmt.setInt(2, ono);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * get userID of a user.
     *
     * @param userString to get the userID of.
     * @return the id.
     */
    @Override
    public int getUIDFromUserString(String userString) {
        String sql = "SELECT UID FROM users WHERE userstring = ?";
        PreparedStatement stmt = null;
        int i = 0;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setString(1, userString);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                i = rs.getInt("uid");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return i;
    }

    @Override
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

    @Override
    public double getDouble(String name) {
        String sql = "select price from material where name=?";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return 5;
    }

    @Override
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

    @Override
    public boolean insertOrder(String json, String userString, double price) {
        String sql = "INSERT INTO orders (uid, ostatus, carport, price, empno) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setInt(1, this.getUIDFromUserString(userString));
            stmt.setInt(2, 0);
            stmt.setString(3, json);
            stmt.setDouble(4, price);
            stmt.setInt(5, -1);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    @Override
    public ArrayList getOrders() {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList getNewOrders() {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders WHERE ostatus = 0";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList getNotDoneOrders() {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders WHERE ostatus = 0 OR ostatus = 1";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList getDoneOrders() {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders WHERE ostatus = 2";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList getOrdersOnONO(int ono) {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders WHERE ono = ?";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setInt(1, ono);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public String getCarport(String userString) {
        String sql = "SELECT carport FROM users WHERE userstring = ?";
        PreparedStatement stmt = null;
        String s = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setString(1, userString);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s = rs.getString("carport");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return s;
    }

    @Override
    public boolean updateOrderStatus(int ono, int oStatus) {
        String sql = "UPDATE orders SET ostatus = ? WHERE ono = ?";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setInt(1, oStatus);
            stmt.setInt(2, ono);
            stmt.executeUpdate();
        } catch (Exception e) {
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

    @Override
    public ArrayList getClaimedOrders(int empno) {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders WHERE empno = ?";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setInt(1, empno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList getNotClaimedOrders() {
        ArrayList<Order> orderArray = new ArrayList();
        Order order = null;
        String sql = "SELECT * FROM orders WHERE empno = -1";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int onoRetrieved = rs.getInt("ono");
                int uidRetrieved = rs.getInt("uid");
                int oStatusRetrieved = rs.getInt("ostatus");
                String carportRetrieved = rs.getString("carport");
                double priceRetrieved = rs.getDouble("price");
                int empNo = rs.getInt("empno");

                order = new Order(onoRetrieved, uidRetrieved, oStatusRetrieved, carportRetrieved, priceRetrieved, empNo);
                orderArray.add(order);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return orderArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList getAllMaterials() {
        ArrayList<Material> materialArray = new ArrayList();
        Material material = null;
        String sql = "SELECT * FROM material";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int mno = rs.getInt("mno");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String name = rs.getString("name");
                int qoh = rs.getInt("qoh");
                int size = rs.getInt("size");
                material = new Material(mno, type, price, name, qoh, size);
                materialArray.add(material);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null){
                    stmt.close();
                    return materialArray;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public boolean updatePriceOnMaterial(int mno, int price) {
        String sql = "UPDATE material SET price = ? WHERE mno = ?";
        PreparedStatement stmt = null;
        try {
            stmt = dbcon.getConnection().prepareStatement(sql);
            stmt.setInt(1, price);
            stmt.setInt(2, mno);
            stmt.executeUpdate();
        } catch (Exception e) {
        }finally{
            try {
                if(stmt != null){
                    stmt.close();
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public boolean newMaterial(String type, int price, String name, int qoh, int size) {
        String sql = "INSERT INTO material(type, price, name, qoh, size) VALUES (?, ?, ?, ?, ?)";
        return false;
    }

}
