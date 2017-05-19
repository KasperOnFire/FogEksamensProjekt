/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialList;

import Database.DataAccessObjectImpl;
import java.util.ArrayList;

/**
 *
 * @author Kristian
 */
public class DatabaseBack
{
    DataAccessObjectImpl DAO;

    public DatabaseBack() throws Exception {
        this.DAO = new DataAccessObjectImpl();
    }
    
    public double getDouble(String name)
    {
        return DAO.getDouble(name);
    }
    
    public ArrayList getAll()
    {
        return DAO.getAllMaterials();
    }
}
