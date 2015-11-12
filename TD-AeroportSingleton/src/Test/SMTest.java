package Test;

import Elements.*;
import SystemManager.SystemManager;
import org.testng.annotations.*;

import java.util.GregorianCalendar;

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
    //region création Ok
    @DataProvider
    public Object[][] createAirportOk()
    {
        return new Object[][]
        {
                {"DEN"}, {"DFW"}, {"DEH"}, {"JPN"}, {"LEF"}
        };
    }

    @Test (groups = { "init" }, dataProvider = "createAirportOk")
    public void testCreateAirportOk(String p1) throws Exception {
        assertEquals(sm.createAirport(p1), new Airport(p1));
    }
    //endregion

    //region Unicité de l'ID
    @DataProvider
    public Object[][] createAirportID()
    {
        return new Object[][]
            {
                    {"DEN"}, {"DFW"}
            };
    }

    @Test (dependsOnMethods = "testCreateAirportOk", dataProvider = "createAirportID")
    public void testCreateAirportID(String p1) throws Exception {
        assertEquals(sm.createAirport(p1), null);
    }
    //endregion

    //region Taille ID
    @DataProvider
    public Object[][] createAirportLenght()
    {
        return new Object[][]
                {
                        {"DENOP"}, {"DF"}
                };
    }

    @Test (dataProvider = "createAirportLenght")
    public void testCreateAirportLenght(String p1) throws Exception
    {
        assertEquals(sm.createAirport(p1), null);
    }
    //endregion
    //endregion

    //region Test Airline
    //region création Ok
    @DataProvider
    public Object[][] createAirlineOk()
    {
        return new Object[][]
                {
                        {"DELTA"}, {"AMER"}, {"JET"}, {"SWEST"}
                };
    }

    @Test (groups = { "init" }, dataProvider = "createAirlineOk")
    public void testCreateAirlineOk(String p1) throws Exception {
        assertEquals(sm.createAirline(p1), new Airline(p1, sm.getDicoAero()));
    }
    //endregion

    //region Unicité de l'ID
    @DataProvider
    public Object[][] createAirlineID()
    {
        return new Object[][]
                {
                        {"AMER"}, {"JET"}
                };
    }
    @Test (dependsOnMethods = "testCreateAirlineOk", dataProvider = "createAirlineID")
    public void testCreateAirlineID(String p1) throws Exception {
        assertEquals(sm.createAirline(p1), null);
    }
    //endregion

    //region Taille ID
    @DataProvider
    public Object[][] createAirlineLength()
    {
        return new Object[][]
                {
                        {"AZERTY"}, {"PANDAROUX"}
                };
    }
    @Test (dataProvider = "createAirlineLength")
    public void testCreateAirlineLength(String p1) throws Exception {
        assertEquals(sm.createAirline(p1), null);
    }
    //endregion
    //endregion

    //region Test Flight
    //region création Ok
    @DataProvider
    public Object[][] createFlightOk()
    {
        return new Object[][]
                {
                        {"AMER", "DEH", "JPN", 2008, 11, 12, "123"},
                        {"SWEST", "DFW", "DEN", 2001, 4, 3, "456"},
                        {"AMER", "JPN", "DFW", 2008, 11, 12, "369"},
                        {"JET", "LEF", "JPN", 2000, 3, 4, "852"},
                        {"DELTA", "JPN", "DFW", 2010, 8, 11, "258"}
                };
    }
    @Test (groups = { "suite" }, dependsOnGroups = { "init.*" }, dataProvider = "createFlightOk")
    public void testCreateFlightOk(String ligne, String orig, String dest, int annee, int mois, int jour, String ID) throws Exception
    {
        GregorianCalendar date = new GregorianCalendar(annee, mois, jour);
        assertEquals(sm.createFlight(ligne, orig, dest, annee, mois, jour, ID),
                new Flight(ID, date, sm.getDicoAir().get(ligne), sm.getDicoAero().get(orig), sm.getDicoAero().get(dest)));
    }
    //endregion

    //region Unicité de l'ID
    @DataProvider
    public Object[][] createFlightDoublon()
    {
        return new Object[][]
                {
                        {"AMER", "JPN", "DEN", 2014, 9, 12, "123"}
                };
    }
    @Test (dependsOnMethods = "testCreateFlightOk", dataProvider = "createFlightDoublon")
    public void testCreateFlightDoublon(String ligne, String orig, String dest, int annee, int mois, int jour, String ID) throws Exception
    {
        assertEquals(sm.createFlight(ligne, orig, dest, annee, mois, jour, ID), null);
    }
    //endregion

    //region Aeropot inexistant
    @DataProvider
    public Object[][] createFlightAeroInex()
    {
        return new Object[][]
                {
                        {"SWEST", "DEI", "DEN", 2003, 7, 3, "486"},
                        {"SWEST", "DEN", "DEI", 2005, 9, 7, "657"}
                };
    }
    @Test (dependsOnMethods = "testCreateFlightOk", dataProvider = "createFlightAeroInex")
    public void testCreateFlightAeroInex(String ligne, String orig, String dest, int annee, int mois, int jour, String ID) throws Exception
    {
        assertEquals(sm.createFlight(ligne, orig, dest, annee, mois, jour, ID), null);
    }
    //endregion

    //region Compagnie inexistante
    @DataProvider
    public Object[][] createFlightCompInex()
    {
        return new Object[][]
                {
                        {"ZOZO", "LEF", "DFW", 2015, 6, 15, "357"}
                };
    }
    @Test (dependsOnMethods = "testCreateFlightOk", dataProvider = "createFlightCompInex")
    public void testCreateFlightCompInex(String ligne, String orig, String dest, int annee, int mois, int jour, String ID) throws Exception
    {
        assertEquals(sm.createFlight(ligne, orig, dest, annee, mois, jour, ID), null);
    }
    //endregion
    //endregion


    //region Test Section
    //region création Ok
    @DataProvider
    public Object[][] createSectionOk()
    {
        return new Object[][]
                {
                        {"AMER", "123", 2, 2, SeatClass.BUSI, "Business1"},
                        {"AMER", "369", 1, 4, SeatClass.FIRST, "First1"},
                        {"AMER", "369", 2, 1, SeatClass.FIRST, "First2"},
                        {"JET", "852", 1, 1, SeatClass.FIRST, "First1"},
                        {"DELTA", "258", 1,1, SeatClass.FIRST, "FirstDelta1"}
                };
    }
    @Test (groups = { "suite" }, dependsOnMethods = "testCreateFlightOk", dataProvider = "createSectionOk")
    public void testCreateSectionOk(String compagnie, String volid, int ligne, int colonne, SeatClass sc, String ID) throws Exception
    {
        assertEquals(sm.createSection(compagnie, volid, ligne, colonne, sc, ID), true);
    }
    //endregion

    //region creation non Ok
    @DataProvider
    public Object[][] createSectionNonOk()
    {
        return new Object[][]
                {
                        {"AMER", "369", 2, -5, SeatClass.FIRST, "First4"}, // Nbre colonne incorrect
                        {"AMER", "123", 4, 1, SeatClass.BUSI, "Business1"}, // Unicité de l'ID
                        {"AMER", "369", 200, 5, SeatClass.ECO, "Economie1"}, // Nbre de ligne incorrect
                        {"SWEST", "657", 2, 1, SeatClass.ECO, "Economie"}, // Vol inexistant
                        {"ZOZO", "123", 2, 2, SeatClass.ECO, "Economie1"} // Compagnie inexistante
                };
    }

    @Test (dependsOnGroups = { "suite.*" }, dataProvider = "createSectionNonOk")
    public void testCreateSectionNonOk(String compagnie, String volid, int ligne, int colonne, SeatClass sc, String ID) throws Exception
    {
        assertEquals(sm.createSection(compagnie, volid, ligne, colonne, sc, ID), false);
    }
    //endregion
    //endregion

    //region Test FindAvailableFlights
    //region il y en a
    @DataProvider
    public Object[][] findFlights()
    {
        return new Object[][]
        {
                {"JPN","DFW"}, // il y a plusieurs vols disponibles
                {"LEF","JPN"} // il y a 1 seul vol disponbile
        };
    }

    // Il y a des vols disponibles
    @Test (dataProvider = "findFlights", dependsOnGroups = {"suite.*"}, dependsOnMethods = "testCreateFlightOk")
    public void testFindAvailableFlightsOk(String orig, String dest) throws Exception
    {
        assertEquals(sm.findAvailableFlights(orig, dest), true);
    }
    //endregion

    //region il n'y en a pas
    @DataProvider
    public Object[][] noFindFlights()
    {
        return new Object[][]
                {
                        {"JPN","DEN"}, // Pas de vol dispo
                        {"LEF", "ZOZ"}, // Destination inexistante
                        {"PAF", "LEF"} // Origine inexistante
                };
    }

    // Il n'y a pas des vols disponibles
    @Test (dataProvider = "noFindFlights", dependsOnGroups = {"suite.*"}, dependsOnMethods = "testCreateFlightOk")
    public void testFindAvailableFlightsNonOk(String orig, String dest) throws Exception
    {
        assertEquals(sm.findAvailableFlights(orig, dest), false);
    }
    //endregion

    //endregion


    //region Test BookSeat
    //region BookSeat Ok
    @DataProvider
    public Object[][] bookSeatOk()
    {
        return new Object[][]
                {
                        {"DELTA", "258", SeatClass.FIRST, 0, 'A'},
                        {"AMER", "123",SeatClass.BUSI, 0, 'A'},
                        {"JET", "852",SeatClass.FIRST, 0, 'A'},
                        {"AMER", "369",SeatClass.FIRST, 0, 'A'}

                };
    }

    // Il y a des vols disponibles
    @Test (dependsOnGroups = {"suite.*", "init.*"}, dependsOnMethods = {"testFindAvailableFlightsOk"}, dataProvider = "bookSeatOk")
    public void testBookSeatOk(String air, String fl, SeatClass sc, int row, char col) throws Exception
    {
        assertEquals(sm.bookSeat(air,fl,sc,row, col), true);
    }

    //endregion

    //region BookSeat NonOk
    @DataProvider
    public Object[][] bookSeatNonOk()
    {
        return new Object[][]
                {
                        {"TH", "483",SeatClass.FIRST, 0, 'A'}, // Compagnie aérienne inexistante},
                        {"AMER", "123",SeatClass.BUSI, 0, 'A'}, // Siège déjà réservé
                        {"AMER", "123",SeatClass.BUSI, 8, 'A'}, // Siège inexistant
                        {"JET", "852",SeatClass.FIRST, 0, 'A'} // Pas de place disponible

                };
    }

    // Il y a des vols disponibles
    @Test (dependsOnGroups = {"suite.*", "init.*"}, dependsOnMethods = {"testFindAvailableFlightsOk", "testBookSeatOk"}, dataProvider = "bookSeatNonOk")
    public void testBookSeatNonOk(String air, String fl, SeatClass sc, int row, char col) throws Exception
    {
        assertEquals(sm.bookSeat(air,fl,sc,row, col), false);
    }

    //endregion
    //endregion
}