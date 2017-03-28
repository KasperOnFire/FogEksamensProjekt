/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasper
 */
public class DBConnectorTest {

    public DBConnectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class DBConnector.
     */
    @Test
    public void testGetConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("getConnection");
        DBConnector instance = new DBConnector();
        Connection con = instance.getConnection();
        boolean expResult = true;
        boolean result = con != null;
        assertEquals(expResult, result);
    }

}
