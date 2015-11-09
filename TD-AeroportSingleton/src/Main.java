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
        sm.createFlight("JET", "DEN", "DEH", 2014, 4, 6, "159");
        //endregion

        //region données création section
        sm.createSection("JET" , "159" , 2 , 2 , SeatClass.ECO, "Eco1");
        sm.createSection("DELTA" , "123" , 1 , 1 , SeatClass.BUSI, "B1");
        sm.createSection( "DELTA" , "123" , 1 , 2 , SeatClass.ECO, "Eco1");
        //endregion

        sm.displaySystemDetails();

        //region données resa vol
        sm.bookSeat("JET", "159", SeatClass.ECO, 0, 'A');
        sm.bookSeat( "DELTA" , "123" , SeatClass.BUSI , 1 , 'A');
        sm.bookSeat( "DELTA" , "123" , SeatClass.ECO , 1 , 'A');
        sm.bookSeat( "DELTA" , "123" , SeatClass.ECO , 1 , 'B');
        sm.bookSeat( "DELTA" , "123" , SeatClass.BUSI , 1 , 'A');
        //endregion



    }
}
