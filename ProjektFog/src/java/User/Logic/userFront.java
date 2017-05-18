package User.Logic;

import User.Logic.DatabaseFront;
import java.util.ArrayList;

public class userFront {

    DatabaseFront DBF = null;

    public userFront() {
        try {
            this.DBF = new DatabaseFront();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean claimUser(String empName, int ono) {
        return DBF.empClaimOrder(empName, ono);
    }

    public boolean updateStatus(int ono, int oStatus) {
        return DBF.updateStatus(ono, oStatus);
    }

    public ArrayList getAllOrders() {
        return DBF.getOrders();
    }
}