/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

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
public class PasswordTest {

    Password instance = null;

    public PasswordTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new Password();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of get_SHA_512_SecurePassword method, of class Password.
     */
    @Test
    public void testGet_SHA_512_SecurePassword() throws Exception {
        String passwordToHash = "testPW";
        String salt = "SalTtEsT123";
        String expResult = "1f26f0afac4a8e1dc276d59faabcffd58b11b722649b07e83256c6c294e5d83c3c5460adaba23bb6f3eb1b9c35f546892e7af40106fae07193aff09bbe7711dd";
        System.out.println(expResult);
        String result = instance.get_SHA_512_SecurePassword(passwordToHash, salt);
        assertEquals(expResult, result);
    }

}
