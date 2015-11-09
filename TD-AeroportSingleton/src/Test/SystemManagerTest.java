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
        sm.createAirport("DEH");
        sm.createAirport("JPN");
        sm.createAirport("JPNPO"); // Nom trop long
        sm.createAirport("DEH"); // Existe déjà
        sm.createAirport("LEF");

        //endregion
        //region données création airline
        sm.createAirline("DELTA");
        sm.createAirline("AMER");
        sm.createAirline("MPOLIKJ"); // Nom trop long
        sm.createAirline("JET");
        sm.createAirline("SWEST");
        sm.createAirline("AMER"); // Existe déjà
        //endregion
        //region données création vol
        sm.createFlight("AMER", "DEH", "JPN", 2008, 11, 12, "123");
        sm.createFlight("AMER", "JPN", "DEN", 2014, 9, 12, "123"); // Unicité de l'ID
        sm.createFlight("SWEST", "DFW", "DEN", 2001, 4, 3, "456");
        sm.createFlight("SWEST", "DEI", "DEN", 2003, 7, 3, "486"); // Aeorport de départ inexistant
        sm.createFlight("AMER", "JPN", "DFW", 2008, 11, 12, "369");
        sm.createFlight("SWEST", "DEN", "DEI", 2005, 9, 7, "657"); // Aeorport d'arrivé inexistant
        sm.createFlight("JET", "LEF", "JPN", 2000, 3, 4, "852");
        sm.createFlight("DELTA", "JPN", "DFW", 2010, 8, 11, "258");
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
        assertEquals(sm.getDicoAero().size(), 5); // Airport
        assertEquals(sm.getDicoAir().size(), 4); // Airline
        assertEquals(sm.getDicoVol().size(), 5); // Vol
    }

    /*
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
    */
}