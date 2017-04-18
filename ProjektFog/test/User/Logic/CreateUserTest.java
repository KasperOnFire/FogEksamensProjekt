/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Logic;

import User.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author T430
 */
public class CreateUserTest {
    
    public CreateUserTest() {
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
     * Test of insertUser method, of class CreateUser.
     */
    @Test
    public void testInsertUser() throws Exception {
        System.out.println("insertUser");
        String username = "";
        String password = "";
        String email = "";
        CreateUser instance = new CreateUser();
        boolean expResult = false;
        boolean result = instance.insertUser(username, password, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfAvaible method, of class CreateUser.
     */
    @Test
    public void testCheckIfAvaible() throws Exception {
        System.out.println("checkIfAvaible");
        String username = "";
        CreateUser instance = new CreateUser();
        boolean expResult = false;
        boolean result = instance.checkIfAvaible(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnUser method, of class CreateUser.
     */
    @Test
    public void testReturnUser() throws Exception {
        System.out.println("returnUser");
        String username = "";
        CreateUser instance = new CreateUser();
        User expResult = null;
        User result = instance.returnUser(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
