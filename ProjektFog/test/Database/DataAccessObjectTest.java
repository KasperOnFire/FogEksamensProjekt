/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import User.*;
import java.util.*;
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
public class DataAccessObjectTest {

    DataAccessObject instance = null;
    Random r = new Random();

    public DataAccessObjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        instance = new DataAccessObjectImpl();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUserByUsername method, of class DataAccessObjectImpl.
     */
    @Test
    public void testGetUserByUsername() throws Exception {
        String username = "test1";
        String expResult = "test1@test1.com";
        User u = instance.getUserByUsername(username);
        String result = u.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdminByUsername method, of class DataAccessObjectImpl.
     */
    @Test
    public void testGetAdminByUsername() throws Exception {
        String username = "admintest";
        String expResult = "test adminsen";
        AdminUser au = instance.getAdminByUsername(username);
        String result = au.getEmpname();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateCarport method, of class DataAccessObjectImpl.
     */
    @Test
    public void testUpdateCarport() throws Exception {
        String jsonString = "{\"carport\":{\"width\":500,\"depth\":500,\"height\":230},"
                + "\"roof\":{\"gableRoof\":false,\"overhang\":{\"sides\":20,\"front\":20,\"back\":20}},"
                + "\"shed\":{\"shed\":false,\"depth\":300,\"doorPlacement\":0,\"side\":\"Foran\",\"rotateDoor\":false}}";
        String userString = "NWHSLHFLZMF0GZEBU9";
        boolean expResult = true;
        boolean result = instance.updateCarport(jsonString, userString);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUIDFromUserString method, of class DataAccessObjectImpl.
     */
    @Test
    public void testGetUIDFromUserString() {
        String userString = "FI3CRTPBP9G638PTHP";
        int expResult = 6;
        int result = instance.getUIDFromUserString(userString);
        assertEquals(expResult, result);
    }
}
