/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tjalfe
 */
public class GarageCalcTest {
    
    public GarageCalcTest() {
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
     * Test of priceCalculatorToString method, of class GarageCalc.
     */
    @Test
    public void testPriceCalculatorToString() {
        System.out.println("priceCalculatorToString");
        int length = 200;
        int width = 200;
        GarageCalc instance = new GarageCalc();
        String expResult = "1200,00";
        String result = instance.priceCalculatorToString(length, width);
        assertEquals(expResult, result);
    }

    /**
     * Test of priceCalculator method, of class GarageCalc.
     */
    @Test
    public void testPriceCalculator() {
        System.out.println("priceCalculator");
        int length = 200;
        int width = 200;
        GarageCalc instance = new GarageCalc();
        double expResult = 1200.0;
        double result = instance.priceCalculator(length, width);
        assertEquals(expResult, result, 0.01);
    }
    
    /**
     * Test of priceCalculator method, of class GarageCalc.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testPriceCalculatorErrorTest() {
        System.out.println("priceCalculator");
        int length = 0;
        int width = 0;
        GarageCalc instance = new GarageCalc();
        double result = instance.priceCalculator(length, width);
    }
    
}
