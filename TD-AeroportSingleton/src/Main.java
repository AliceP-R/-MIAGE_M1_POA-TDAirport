import Elements.SeatClass;
import MesExceptions.doublonException;
import MesExceptions.lenghtException;
import SystemManager.SystemManager;

public class Main {

    public static void main(String[] args) throws doublonException, lenghtException {
        SystemManager sm = SystemManager.getInstance();


        //region données création airport
        System.err.println("Cr\u00e9ation des donn\u00e9es a\u00e9roport ");
        sm.createAirport("DEN");
        sm.createAirport("DFW");
        sm.createAirport("DEH");
        sm.createAirport("JPN");
        sm.createAirport("JPNPO"); // Nom trop long
        sm.createAirport("DEH"); // Existe déjà
        sm.createAirport("LEF");

        sm.displaySystemDetails();

        //endregion

        //region données création airline
        System.err.println("\nCr\u00e9ation des donn\u00e9es compagnie a\u00e9rienne ");
        sm.createAirline("DELTA");
        sm.createAirline("AMER");
        sm.createAirline("MPOLIKJ"); // Nom trop long
        sm.createAirline("JET");
        sm.createAirline("SWEST");
        sm.createAirline("AMER"); // Existe déjà
        //endregion

        //region données création vol
        System.err.println("\nCr\u00e9ation des donn\u00e9es vol ");
        sm.createFlight("AMER", "DEH", "JPN", 2008, 11, 12, "123");
        sm.createFlight("AMER", "JPN", "DEN", 2014, 9, 12, "123"); // Unicité de l'ID
        sm.createFlight("SWEST", "DFW", "DEN", 2001, 4, 3, "456");
        sm.createFlight("SWEST", "DEI", "DEN", 2003, 7, 3, "486"); // Aeorport de départ inexistant
        sm.createFlight("AMER", "JPN", "DFW", 2008, 11, 12, "369");
        sm.createFlight("SWEST", "DEN", "DEI", 2005, 9, 7, "657"); // Aeorport d'arrivé inexistant
        sm.createFlight("JET", "LEF", "JPN", 2000, 3, 4, "852");
        sm.createFlight("DELTA", "JPN", "DFW", 2010, 8, 11, "258");
        //endregion

        //region données création section
        System.err.println("\nCr\u00e9ation des donn\u00e9es section ");
        sm.createSection("AMER", "123", 2, 2, SeatClass.BUSI, "Business1");
        sm.createSection("AMER", "369", 2, -5, SeatClass.FIRST, "First3"); // Nombre de colonnes incorrect
        sm.createSection("AMER", "123", 4, 1, SeatClass.BUSI, "Business1"); // Unicité de l'id
        sm.createSection("AMER", "369", 1, 4, SeatClass.FIRST, "First1");
        sm.createSection("AMER", "369", 2, 1, SeatClass.FIRST, "First2");
        sm.createSection("AMER", "369", 200, 5, SeatClass.ECO, "Economie1"); // Nombre de ligne incorrect
        sm.createSection("SWEST", "657", 2, 1, SeatClass.ECO, "Economie"); // Vol inexistant
        sm.createSection("JET", "852", 1, 1, SeatClass.FIRST, "First1");
        sm.createSection("ZOZO", "123", 2, 2, SeatClass.ECO, "Economie1"); // Compagnie inexistante
        sm.createSection("DELTA", "258", 1,1, SeatClass.FIRST, "FirstDelta1");
        //endregion

        //sm.displaySystemDetails();

        //region données resa vol
        System.out.println("Avant réservation");
        sm.findAvailableFlights("JPN","DFW");
        sm.findAvailableFlights("JPN","DEN"); // Pas de vol
        sm.findAvailableFlights("LEF","JPN"); // 1 seul vol

        System.err.println("\nCr\u00e9ation des donn\u00e9es resa ");
        sm.bookSeat("DELTA", "258", SeatClass.FIRST, 0, 'A');
        sm.bookSeat("TH", "483",SeatClass.FIRST, 0, 'A'); // Compagnie aérienne inexistante
        sm.bookSeat("AMER", "123",SeatClass.BUSI, 0, 'A' );
        sm.bookSeat("AMER", "123",SeatClass.BUSI, 0, 'A' ); // Siège déjà réservé
        sm.bookSeat("AMER", "123",SeatClass.BUSI, 8, 'A' ); // Siège inexistant
        sm.bookSeat("JET", "852",SeatClass.FIRST, 0, 'A');
        sm.bookSeat("JET", "852",SeatClass.FIRST, 0, 'A'); // Pas de place disponible
        sm.bookSeat("AMER", "369",SeatClass.FIRST, 0, 'A');


        System.out.println("Après réservation");
        sm.findAvailableFlights("JPN","DFW"); // Il n'y a plus qu'un seul vol incomplet
        sm.findAvailableFlights("LEF","JPN"); // Aucun vol incomplet
        //endregion

       /* sm.displaySystemDetails();

        sm.findAvailableFlights("LON","DEH");
        sm.findAvailableFlights("DEH","LON");*/
    }
}
