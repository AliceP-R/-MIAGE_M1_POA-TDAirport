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
    public HashMap<String, Airport> getDicoAero() {
        return dicoAero;
    }

    public Airport createAirport(String code){
        Airport aero = null;

        if(code.length() != 3)
        {
            System.err.println("Cet ID n'a pas 3 caract\u00e8res.");
        }
        else if(dicoAero.containsKey(code))
        {
            System.err.println("Cet ID existe d\u00e9j\u00e0.");
        }
        else
        {
            aero = new Airport(code);
            dicoAero.put(aero.getName(), aero);
        }
        return aero;
    }
    // endregion



    //region Airline
    private HashMap<String, Airline> dicoAir = new HashMap<>();
    public HashMap<String, Airline> getDicoAir() {
        return dicoAir;
    }

    public Airline createAirline(String nom)
    {
        Airline ligne = null;

        if(nom.length() > 5)
            System.err.println("Votre code a trop de caract\u00e8res");

        else if(dicoAir.containsKey(nom))
            System.err.println("Ce code existe déjà.");

        else
        {
            ligne = new Airline(nom, dicoAero);
            dicoAir.put(ligne.getIdentifiant(), ligne);
        }

        return ligne;
    }
    //endregion



    //region Flight
    private HashMap<String, Flight> dicoVol = new HashMap<>();
    public HashMap<String, Flight> getDicoVol() {
        return dicoVol;
    }

    // n = compagnie
    public Flight createFlight(String n,  String  orig,  String  dest,  int year,  int month,  int day,  String id)
    {
        Airline ligne = dicoAir.get(n);
        Flight vol = null;
        if(ligne == null)
        {
            System.err.println("Cette compagnie a\u00e9rienne n'existe pas");
        }
        else
        {
            Calendar date = new GregorianCalendar(year, month, day);
            vol = ligne.createFlight(orig, dest, date, id);
            if(vol != null)
                dicoVol.put(vol.getID(), vol);
        }

        return vol;
    }
    //endregion

    //region Section
    public FlightSection createSection(String air, String flID, int  rows,  int  cols,  SeatClass  s, String id)
    {
        FlightSection creation = null;
        Airline ligne = dicoAir.get(air);
        if(ligne != null)
            creation = ligne.createSection(flID, rows, cols, s, id);
        else
            System.err.println("Cette compagnie n'existe pas.");

        return creation;
    }
    //endregion


    public void findAvailableFlights(String orig, String dest)
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

        if(res.size() == 1)
            System.out.println("Il y a 1 vol incomplet en partance de " + orig + " pour " + dest +" :");
        else if(res.size() > 1)
            System.out.println("Il y a "+res.size()+" vols incomplets en partance de " + orig + " pour " + dest +" :");
        if(!res.isEmpty())
            System.out.println(res.toString());
        else
            System.out.println("Il n'y a aucun vol incomplet en partance de " + orig + " pour " + dest +".");
    }

    public void bookSeat(String air, String fl, SeatClass sc, int row, char col)
    {
        Airline compagnie = dicoAir.get(air);
        Flight vol = null;
        HashSet<FlightSection> fls;

        SeatID sid = new SeatID(row, col);

        if(compagnie == null)
        {
            System.err.println("La compagnie a\u00e9rienne "+air+" n'existe pas.");
        }
        else
            vol = compagnie.getDicoVol().get(fl);

        if(compagnie != null && vol == null)
        {
            System.err.println("Le vol "+fl+" pour la compagnie "+air+" n'existe pas.");
        }
        else if(compagnie != null && vol != null)
        {
            fls = vol.findSection(sc);
            boolean resa = false;

            if(fls.isEmpty()) {
                System.err.println("Il n'y a pas de place dans une section " + sc +" pour ce vol.");
                resa = true;
            }
            Iterator<FlightSection> it = fls.iterator();
            while(resa == false && it.hasNext())
            {
                FlightSection fs = it.next();
                if(fs.hasAvailableSeat())
                    resa = fs.bookSeat(sid);
                else
                    System.err.println("Section compl\u00e8te");
            }
        }
    }

    public void displaySystemDetails()
    {

        //region affiche aéroports
        System.out.println("Liste des a\u00e9roports :");
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
        System.out.println("----------------------------------");
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
        System.out.println("----------------------------------");
        //endregion
    }
}
