package SystemManager;

import Elements.Airport;
import java.util.HashSet;

/**
 * Created by Alice on 02/11/2015.
 *
 */
public class SystemManager
{
    private static SystemManager ourInstance = new SystemManager();

    public static SystemManager getInstance() {
        return ourInstance;
    }

    public HashSet<Airport> getListeaero() {
        return listeaero;
    }

    private HashSet<Airport> listeaero = new HashSet<>();
    private SystemManager(){}

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

    /*
    private void createAirline()
    {

    }
    private void createFlight()
    {

    }

    private void createSection()
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
