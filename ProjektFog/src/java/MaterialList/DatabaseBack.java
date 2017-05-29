/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialList;

import Database.DataAccessObject;
import Database.DataAccessObjectImpl;
import java.util.ArrayList;

/**
 *
 * @author Kristian
 */
public class DatabaseBack {

    DataAccessObject DAO;

    public DatabaseBack() throws Exception {
        this.DAO = new DataAccessObjectImpl();
    }

    public ArrayList getAll() {
        return DAO.getAllMaterials();
    }
}
