import SystemManager.SystemManager;

public class Main {

    public static void main(String[] args)
    {
        SystemManager sm = SystemManager.getInstance();

        //region donn�es cr�ation airport
        sm.createAirport("DEN");
        sm.createAirport("DFW");
        sm.createAirport("LON");
        sm.createAirport("CDG");
        sm.createAirport("JPN");
        sm.createAirport("DEH");
        //endregion

        //region donn�es cr�ation airline
        sm.createAirline("DELTA");
        sm.createAirline("AIRFR");
        sm.createAirline("SWEST");
        //endregion

        //region donn�es cr�ation vol
        sm.createFlight("DELTA","DEN","LON",2008,11,12,"123");
        sm.createFlight("DELTA","DEN","DEH",2009,8,9,"567");
        sm.createFlight("DELTA","DEN","NCE",2010,9,8,"456"); // a�roport de destination inexistant
        sm.createFlight("DELTA","DEN","DEH",2011,5,7,"567"); // Unicit�
        //endregion

    }
}
