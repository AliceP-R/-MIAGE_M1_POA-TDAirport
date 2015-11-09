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
    private HashMap<String, Airport> dicoAero = new HashMap<>();
    public HashMap<String, Airport> getDicoAero()
    {
        return dicoAero;
    }

    public void createAirport(String code)
    {
        Airport aero = new Airport(code);

        if(code.length() != 3)
            System.err.println("Votre code n'a pas le bon nombre de caractères");

        else if(dicoAero.containsKey(aero.getName()))
            System.err.println("Ce code existe déjà");

        else
        dicoAero.put(aero.getName(), aero);
    }
    // endregion



    //region Airline
    private HashMap<String, Airline> dicoAir = new HashMap<>();
    public HashMap<String, Airline> getDicoAir()
    {
        return dicoAir;
    }

    public void createAirline(String nom)
    {
        Airline ligne = new Airline(nom, dicoAero);

        if(nom.length() > 5)
            System.err.println("Votre code a trop de caract\u00e8res");

        else if(dicoAir.containsKey(ligne.getIdentifiant()))
            System.err.println("Ce code existe déjà.");

        else
            dicoAir.put(ligne.getIdentifiant(), ligne);
    }
    //endregion



    //region Flight
    private HashMap<String, Flight> dicoVol = new HashMap<>();
    public HashMap<String, Flight> getDicVol()
    {
        return dicoVol;
    }

    // n = compagnie
    public void createFlight(String n,  String  orig,  String  dest,  int year,  int month,  int day,  String id)
    {
        Airline ligne = dicoAir.get(n);
        if(ligne == null)
        {
            System.err.println("Cette compagnie a\u00e9rienne n'existe pas");
        }
        else
        {
            Calendar date = new GregorianCalendar(year, month, day);
            Flight vol = ligne.createFlight(orig, dest, date, id);
            if(vol != null)
                dicoVol.put(vol.getID(), vol);
        }

    }
    //endregion

    //region Section
    public void createSection(String air, String flID, int  rows,  int  cols,  SeatClass  s, String id)
    {
        Airline ligne = dicoAir.get(air);
        if(ligne != null)
            ligne.createSection(flID, rows, cols, s, id);
        else
            System.err.println("Cette compagnie n'existe pas.");
    }
    //endregion


    //region Section
    private HashSet<Flight> findAvailableFlights(String orig, String dest)
    {
        Airport origine = dicoAero.get(orig);
        Airport destination = dicoAero.get(dest);
        HashSet<Flight> res = new HashSet();

        Set clef = dicoVol.keySet();
        Iterator it = clef.iterator();
        while(it.hasNext())
        {
            Flight f = dicoVol.get(it.next());
            if(f.getDestination().equals(destination) && f.getOrigine().equals(origine) && f.hasSeats())
                res.add(f);
        }

        return res;
    }

    public void bookSeat(String air, String fl, SeatClass sc, int row, char col)
    {
        Airline compagnie = dicoAir.get(air);
        Flight vol = compagnie.getDicoVol().get(fl);
        HashSet<FlightSection> fls;

        SeatID sid = new SeatID(row, col);

        if(compagnie == null)
        {
            System.err.println("Cette compagnie aérienne n'existe pas.");
        }
        else if(vol == null)
        {
            System.err.println("Pas de " +sc+" pour ce vol.");
        }
        else
        {
            fls = vol.findSection(sc);
            boolean resa = false;
            Iterator<FlightSection> it = fls.iterator();
            while(resa == false && it.hasNext())
            {
                resa = ((FlightSection)it.next()).bookSeat(sid);
            }

            if(resa == false)
                System.err.println("Le si\u00e8ge " +sid+" n'existe pas pour la section "+sc+" du vol "+fl+" de la compagnie "+air);
        }
    }

    public void displaySystemDetails()
    {

        //region affiche aéroports
        System.out.println("Liste des aéroports :");
        System.out.println("----------------------------------");
        Set<String> listeClef = dicoAero.keySet();
        Iterator it_aero = listeClef.iterator();
        while(it_aero.hasNext())
        {
            Object aero = it_aero.next();
            System.out.println(dicoAero.get(aero));
        }
        System.out.println("----------------------------------");
        //endregion

        //region affiche airline
        System.out.println("Liste des compagnies a\u00e9riennes :");
        System.out.println("----------------------------------");
        Set<String> clef = dicoAir.keySet();
        Iterator it_air = clef.iterator();
        while(it_air.hasNext())
        {
            Object ligne = it_air.next();
            System.out.println(dicoAir.get(ligne));
        }
        //endregion

        //region affiche vol
        System.out.println("Liste des vols :");
        System.out.println("----------------------------------");
        Set<String> setClef = dicoVol.keySet();
        Iterator it_vol = setClef.iterator();
        while(it_vol.hasNext())
        {
            Object vol = it_vol.next();
            System.out.println(dicoVol.get(vol));
        }
        //endregion
    }
}
