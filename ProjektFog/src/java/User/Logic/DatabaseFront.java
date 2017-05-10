package User.Logic;

import Database.DataAccessObjectImpl;

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

}
