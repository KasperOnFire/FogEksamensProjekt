/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import User.AdminUser;
import User.User;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author T430
 */
public interface DataAccessObject {

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
    boolean createAdmin(String username, String password, String empno, String empname) throws SQLException, UnsupportedEncodingException;

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
    boolean createUser(String username, String password, String email) throws SQLException, UnsupportedEncodingException;

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
    AdminUser getAdminByUsername(String username) throws SQLException;

    ArrayList getAllMaterials();

    String getCarport(String userString);
    
    int getEmpNoByUsername(String empName) throws SQLException;

    ArrayList getClaimedOrders(int empno);

    ArrayList getDoneOrders();

    ArrayList getNewOrders();

    ArrayList getNotClaimedOrders();

    ArrayList getNotDoneOrders();

    ArrayList getOrders();

    ArrayList getOrdersOnONO(int ono);

    /**
     * get userID of a user.
     *
     * @param userString to get the userID of.
     * @return the id.
     */
    int getUIDFromUserString(String userString);

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
    User getUserByUsername(String username) throws SQLException;

    boolean insertOrder(String json, String userString, double price);

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
    boolean updateCarport(String jsonString, String userString) throws SQLException;

    boolean updateEMPonOrder(String empName, int ono);

    boolean updateOrderStatus(int ono, int oStatus);

    boolean updatePriceOnMaterial(int mno, int price);

}
