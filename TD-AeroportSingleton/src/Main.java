import SystemManager.SystemManager;

public class Main {

    public static void main(String[] args)
    {
        SystemManager sm = SystemManager.getInstance();

        //region données création airport
        sm.createAirport("DEN");
        sm.createAirport("DFW");
        sm.createAirport("LON");
        sm.createAirport("CDG");
        sm.createAirport("JPN");
        sm.createAirport("DEH");
        //endregion

        //region données création airline
        sm.createAirline("DELTA");
        sm.createAirline("AIRFR");
        sm.createAirline("SWEST");
        //endregion

        //region données création vol
        sm.createFlight("DELTA","DEN","LON",2008,11,12,"123");
        sm.createFlight("DELTA","DEN","DEH",2009,8,9,"567");
        sm.createFlight("DELTA","DEN","NCE",2010,9,8,"456"); // aéroport de destination inexistant
        sm.createFlight("DELTA","DEN","DEH",2011,5,7,"567"); // Unicité
        //endregion

    }
}
