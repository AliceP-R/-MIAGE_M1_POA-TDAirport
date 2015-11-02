package Test;

import Elements.Airport;
import SystemManager.SystemManager;
import org.testng.annotations.*;
import java.util.Iterator;

import static org.testng.Assert.*;

/**
 * Created by Alice on 02/11/2015.
 */
public class SystemManagerTest {

   SystemManager sm;

    @BeforeClass
    public void setUp() throws Exception
    {
        sm = SystemManager.getInstance();
        sm.createAirport("AZE");
        sm.createAirport("AZ");
        sm.createAirport("AEZ");
        sm.createAirport("ZAE");
        sm.createAirport("AZE");
        sm.createAirport("ZAE");
        sm.createAirport("Z");
        sm.createAirport("ZAERT");
        sm.createAirport("EAZ");
    }

    @AfterClass
    public void tearDown() throws Exception
    {
        sm = null;
    }

    @Test
    public void testNonDoublon() throws Exception
    {
        assertEquals(sm.getListeaero().size(), 4);
    }

    @Test
    public void testTailleOk() throws Exception
    {
        Iterator i=sm.getListeaero().iterator();
        while(i.hasNext())
        {
            assertEquals(((Airport)i.next()).getName().length(), 3);
        }
    }
}