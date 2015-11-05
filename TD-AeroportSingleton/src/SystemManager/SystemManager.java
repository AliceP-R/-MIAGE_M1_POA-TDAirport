package SystemManager;

import Elements.*;

import java.util.*;

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
            System.err.println("Votre code n'a pas le bon nombre de caract�res");

        else if(listeaero.contains(test))
            System.err.println("Ce code existe d�j�");

        else
            listeaero.add(new Airport(code));
    }
    // endregion

    //region Airline
    private HashSet<Airline> listeairl = new HashSet<>();
    public HashSet<Airline> getListeairl()
    {
        return listeairl;
    }
    public void createAirline(String nom)
    {
        Airline test = new Airline(nom, listeaero);

        if(nom.length() != 5)
            System.err.println("Votre code n'a pas 5 caract\u00e8res");

        else if(listeairl.contains(test))
            System.err.println("Ce code existe d�j�.");

        else
            listeairl.add(new Airline(nom, listeaero));
    }
    //endregion

    //region Flight
    private Airline trouverAirline(String n)
    {
        Iterator it = listeairl.iterator();
        Airline parcours;
        while(it.hasNext())
        {
            parcours = (Airline) it.next();
            if(parcours.getIdentifiant().equals(n))
            {
                return parcours;
            }
        }

        return null;
    }
    public void createFlight(String n,  String  orig,  String  dest,  int year,  int month,  int day,  String id)
    {
        Airline ligne = trouverAirline(n);
        if(ligne == null)
        {
            System.err.println("Cette compagnie a\u00e9rienne n'existe pas");
        }
        else
        {
            Calendar date = new GregorianCalendar(year, month, day);
            Flight vol = ligne.createFlight(orig, dest, date, id);
        }

    }

    //endregion

 //    cr�ee  une  sec- tion tarifaire, de classe s, pour un vol identifi�e par flID, associ�e `a une compagnie air, en invoquant l�op�eration createSection() de la classe Airline. La section contiendra le nombre de lignes et de colonnes.


    //region Section
    /*


    private void createSection(String air, String flID, int rows, int cols, SeatClass s)
    {

    }
    //endregion





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
