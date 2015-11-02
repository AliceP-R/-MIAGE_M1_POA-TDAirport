package SystemManager;

import Elements.*;

import java.util.HashSet;

/**
 * Created by Alice on 02/11/2015.
 *
 */
public class SystemManager
{
    //region Methodes Singleton
    private static SystemManager ourInstance = new SystemManager();
    public static SystemManager getInstance() {
        return ourInstance;
    }
    //endregion

    //Constructeur
    private SystemManager(){}

    //region Airport
    private HashSet<Airport> listeaero = new HashSet<>();
    public HashSet<Airport> getListeaero() {
        return listeaero;
    }
    public void createAirport(String code)
    {
        Airport test = new Airport(code);

        if(code.length() != 3)
            System.err.println("Votre code n'a pas le bon nombre de caractères");

        else if(listeaero.contains(test))
            System.err.println("Ce code existe déjà");

        else
            listeaero.add(new Airport(code));
    }
    // endregion

    public void test()
    {
        FlightSection fs = new FlightSection(SeatClass.BUSI, 3,5);
    }

 //    cr´ee  une  sec- tion tarifaire, de classe s, pour un vol identifi´e par flID, associ´e `a une compagnie air, en invoquant l’op´eration createSection() de la classe Airline. La section contiendra le nombre de lignes et de colonnes.


    //region Section
    /*


    private void createSection(String air, String flID, int rows, int cols, SeatClass s)
    {

    }
    //endregion

    private void createAirline()
    {

    }
    private void createFlight()
    {

    }



    private void ?ndAvailableFlights()
    {

    }

    private void bookSeat()
    {

    }

    private void displaySystemDetails()
    {

    }
    */
}
