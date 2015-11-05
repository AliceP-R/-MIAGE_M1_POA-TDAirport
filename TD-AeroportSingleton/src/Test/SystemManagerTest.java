package Test;

import Elements.Airline;
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

        //region données création airport
        sm.createAirport("DEN");
        sm.createAirport("DFW");
        sm.createAirport("LON");
        sm.createAirport("DEN"); // Pas d'unicité
        sm.createAirport("CDG");
        sm.createAirport("JPN");
        sm.createAirport("DEN"); //  Pb d ’ u n i c i t e
        sm.createAirport("DE"); //  I n v a l i d e
        sm.createAirport("DEH");
        sm.createAirport("DRIrdn3"); //  I n v a l i d e

        //endregion
        //region données création airline
        sm.createAirline("DELTA");
        sm.createAirline("AIRFR");
        sm.createAirline("AMER");
        sm.createAirline("JET");
        sm.createAirline("DELTA"); // Unicité
        sm.createAirline("SWEST");
        sm.createAirline("FRONTIER"); // Invalide
        //endregion
        //region données création vol
        sm.createFlight("DELTA","DEN","LON",2008,11,12,"123");
        sm.createFlight("DELTA","DEN","DEH",2009,8,9,"567");
        sm.createFlight("DELTA","DEN","NCE",2010,9,8,"456"); // aéroport de destination inexistant
        sm.createFlight("DELTA","DEN","DEH",2011,5,7,"567"); // Unicité
        //endregion
    }

    @AfterClass
    public void tearDown() throws Exception
    {
        sm = null;
    }

    @Test
    public void testNonDoublon() throws Exception
    {
        assertEquals(sm.getListeaero().size(), 6); // Airport
        assertEquals(sm.getListeairl().size(), 5); // Airline
    }

    @Test
    public void testTailleOk() throws Exception
    {
        Iterator i;

        //region Airport
        i=sm.getListeaero().iterator();
        while(i.hasNext())
        {
            assertEquals(((Airport)i.next()).getName().length(), 3);
        }
        //endregion

        //region Airline
        i=sm.getListeairl().iterator();
        while(i.hasNext())
        {
            boolean taille;
            taille = ((Airline)i.next()).getIdentifiant().length() <= 5;
            assertEquals(taille, true);
        }
        //endregion
    }
}