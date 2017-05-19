package User.Logic;

import Database.DataAccessObjectImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseFront {

    DataAccessObjectImpl DAO;

    public DatabaseFront() throws Exception {
        this.DAO = new DataAccessObjectImpl();
    }

    public boolean addOrder(String json, String userString, double price) {
        if (true) {
            DAO.insertOrder(json, userString, 10);
            return true;
        }
        return false;
    }

    public void saveCarport(String userString, String json) throws SQLException {
        DAO.updateCarport(json, userString);
    }

    public ArrayList getOrders() {
        return DAO.getOrders();
    }

    public ArrayList getNewOrders() {
        return DAO.getNewOrders();
    }

    public ArrayList getDoneOrders() {
        return DAO.getDoneOrders();
    }

    public ArrayList getNotDoneOrders() {
        return DAO.getNotDoneOrders();
    }

    public ArrayList getOrdersOnONO(int ONO) {
        return DAO.getOrdersOnONO(ONO);
    }

    public boolean empClaimOrder(String empName, int ono) {
        return DAO.updateEMPonOrder(empName, ono);
    }

    boolean updateStatus(int ono, int oStatus) {
        return DAO.updateOrderStatus(ono, oStatus);
    }

    public ArrayList getClaimedOrders(int empno) {
        return DAO.getClaimedOrders(empno);
    }

    public ArrayList getNotClaimed() {
        return DAO.getNotClaimedOrders();
    }

    public  ArrayList GetAllMaterials() {
        return DAO.getAllMaterials();
    }

    public boolean updatePrice(int mno, int price) {
        return DAO.updatePriceOnMaterial(mno, price);
    }

    public boolean newMaterial(String type, int price, String name, int qoh, int size) {
        return DAO.newMaterial(type, price, name, qoh, size);
    }
}
