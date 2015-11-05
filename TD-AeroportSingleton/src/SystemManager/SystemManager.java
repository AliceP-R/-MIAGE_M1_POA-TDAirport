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
            System.err.println("Votre code n'a pas le bon nombre de caractères");

        else if(listeaero.contains(test))
            System.err.println("Ce code existe déjà");

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

        if(nom.length() > 5)
            System.err.println("Votre code a trop de caract\u00e8res");

        else if(listeairl.contains(test))
            System.err.println("Ce code existe déjà.");

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

    //region Section
    public void createSection(String air, String flID, int  rows,  int  cols,  SeatClass  s)
    {
        Airline ligne = trouverAirline(air);
        if(ligne != null)
            ligne.createSection(flID, rows, cols, s);
        else
            System.err.println("Cette compagnie n'existe pas.");
    }
    //endregion


    //region Section
    private void finndAvailableFlights()
    {

    }

    private void bookSeat()
    {

    }

    public void displaySystemDetails()
    {

        //region affiche aéroports
        System.out.println("Liste des aéroports :");
        System.out.println("----------------------------------");
        Iterator it_aero = listeaero.iterator();
        while(it_aero.hasNext())
        {
            System.out.println(((Airport)it_aero.next()).toString());
        }
        System.out.println("----------------------------------");
        //endregion

        //region affiche airline
        Iterator it_air = listeairl.iterator();
        Iterator it_compagnie;
        Airline compagnie;
        Flight vol;
        while(it_air.hasNext())
        {
            compagnie = (Airline)it_air.next();
            System.out.println("Pour la compagnie" + compagnie.getIdentifiant());
            it_compagnie = compagnie.getListeVol().iterator();
            while(it_compagnie.hasNext())
            {
                vol = (Flight) it_compagnie.next();
                System.out.println(vol.toString());
            }
            System.out.println("---------------------------------");
        }
        //endregion

    }
}
