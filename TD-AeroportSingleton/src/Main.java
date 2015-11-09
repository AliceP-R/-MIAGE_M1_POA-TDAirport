import Elements.SeatClass;
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
        sm.createAirline("AMER");
        sm.createAirline("JET");
        sm.createAirline("SWEST");
        //endregion

        //region données création vol
        sm.createFlight("DELTA","DEN","LON",2008,11,12,"123");
        sm.createFlight("DELTA","DEN","DEH",2009,8,9,"567");
        //endregion

       sm.displaySystemDetails();

        //region données création section

        sm.createSection("JET" , "123" , 2 , 2 , SeatClass.ECO);
        /*
        sm.createSection("JET" , "123" , 1 , 3 , SeatClass.ECO);
        sm.createSection("JET" , "123" , 2 , 3 , SeatClass.FIRST);
        sm.createSection("DELTA" , "123" , 1 , 1 , SeatClass.BUSI);
        sm.createSection( "DELTA" , "123" , 1 , 2 , SeatClass.ECO);
        sm.createSection( "SWSERTT" , "123" , 5 , 5 , SeatClass.ECO); // Invalide
        //endregion
        */

    }
}
