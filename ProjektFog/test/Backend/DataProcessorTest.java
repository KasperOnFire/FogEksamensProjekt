/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Carport.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DataProcessorTest {

    DataProcessor instance = null;

    public DataProcessorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            instance = new DataProcessor();
        } catch (Exception ex) {
            Logger.getLogger(DataProcessorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseJson method, of class DataProcessor.
     */
    @Test
    public void testParseJson() {
        String json = "{\"guiCarport\":{\"width\":500,\"depth\":700,\"height\":230},\"guiRoof\":{\"gableRoof\":false,\"overhang\":{\"sides\":20,\"front\":20,\"back\":20}},\"guiShed\":{\"shed\":false,\"depth\":300,\"doorPlacement\":0,\"side\":\"Foran\",\"rotateDoor\":false}}";
        String expResult = "500x230x700";
        Carport jsonCarport = instance.parseJson(json);
        String result = jsonCarport.getBase().toString();
        assertEquals(expResult, result);
    }

}
