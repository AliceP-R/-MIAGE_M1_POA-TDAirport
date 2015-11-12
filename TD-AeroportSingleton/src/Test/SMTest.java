package Test;

import Elements.Airport;
import MesExceptions.doublonException;
import MesExceptions.lenghtException;
import SystemManager.SystemManager;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Created by Alice on 10/11/2015.
 */
public class SMTest {

    private SystemManager sm;

    @BeforeClass
    public void setUp() throws Exception
    {
        sm = SystemManager.getInstance();
    }

    @AfterClass
    public void tearDown() throws Exception
    {
        sm = null;
    }

    //region Test Airport
    @DataProvider
    public Object[][] createAirportOk()
    {
        return new Object[][]
        {
                {"DEN"}, {"DFW"}, {"DEH"}, {"JPN"}
        };
    }

    @Test (dataProvider = "createAirportOk")
    public void testCreateAirportOk(String p1) throws Exception, lenghtException {
        assertEquals(sm.createAirport(p1), new Airport(p1));
    }

    @DataProvider
    public Object[][] createAirportID()
    {
        return new Object[][]
            {
                    {"DEN"}, {"DFW"}
            };
    }

    @Test (dependsOnMethods = "testCreateAirportOk", dataProvider = "createAirportID", expectedExceptions = doublonException.class)
    public void testCreateAirportID(String p1) throws Exception, lenghtException {
        assertEquals(sm.createAirport(p1), null);
    }

    @DataProvider
    public Object[][] createAirportLenght()
    {
        return new Object[][]
                {
                        {"DENOP"}, {"DF"}
                };
    }

    @Test (dataProvider = "createAirportLenght", expectedExceptions = lenghtException.class)
    public void testCreateAirportLenght(String p1) throws Exception, lenghtException
    {
        assertEquals(sm.createAirport(p1), null);
    }

    //endregion

    //region Test Airline
    @Test
    public void testCreateAirline() throws Exception {

    }
    //endregion

    @Test
    public void testCreateFlight() throws Exception {

    }

    @Test
    public void testCreateSection() throws Exception {

    }

    @Test
    public void testFindAvailableFlights() throws Exception {

    }

    @Test
    public void testBookSeat() throws Exception {

    }
}