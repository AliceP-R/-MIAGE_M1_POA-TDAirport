package Elements;

import java.util.*;

/**
 * Created by Alice on 02/11/2015.
 */
public class Flight
{
    private String ID;
    private Calendar date;
    private Airline al;
    private Airport origine;
    private Airport destination;
    private HashMap<String, FlightSection> dicoSec;

    public Flight(String ID, Calendar date, Airline al, Airport origine, Airport destination)
    {
        this.ID = ID;
        this.date = date;
        this.al = al;
        this.origine = origine;
        this.destination = destination;
        this.dicoSec = new HashMap<>();
    }

    //region Accesseurs
    public Airport getDestination() {
        return destination;
    }

    public String getID() {
        return ID;
    }

    public Calendar getDate() {
        return date;
    }

    public Airline getAl() {
        return al;
    }

    public Airport getOrigine() {
        return origine;
    }
    //endregion

    // renvoie true si il y a au moins une section tarifaire
    public boolean hasSection()
    {
        if(dicoSec.isEmpty())
            return  false;

        return true;
    }

    // renvoie true si il reste des sièges disponibles pour ce vol
    public boolean hasSeats()
    {
        boolean res=false;
        if(hasSection())
        {
            Set<String> clef = dicoSec.keySet();
            Iterator ite = clef.iterator();
            while(ite.hasNext())
            {
                Object sec = ite.next();
                if(dicoSec.get(sec).hasAvailableSeat())
                    res= true;
            }
        }
        else
            System.err.println("Il n'y a pas de section d\u00e9finie pour ce vol.");

        return res;
    }

    // si le nombre de rangé et le nombre de siège par ranger est correct, créé une nouvelle section tarifaire pour ce vol
    public boolean createSection(int rows, int cols, SeatClass s, String id)
    {
        boolean res=true;
        if(rows > 100 && rows < 0)
        {
            res=false;
            System.err.println("Nombre de rangée incorrect.");
        }
        else if(cols > 10 && cols < 0)
        {
            res = false;
            System.err.println("Nombre de sièges par rangée incorrect.");
        }
        else
        {
            FlightSection ajout = new FlightSection(id, s, rows, cols);
            dicoSec.put(ajout.getIdentifiant(), ajout);
        }

        return res;
    }


    // id de la section
    public boolean bookSeat(String id)
    {
        boolean res=false;
        if(this.hasSeats())
        {
            FlightSection fs = dicoSec.get(id);
            if(fs != null)
            {
                res=fs.bookSeat();
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return ID.equals(flight.ID);

    }

    @Override
    public int hashCode()
    {
        return ID.hashCode();
    }

    private String afficheDate()
    {
        return ""+date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "Détails du vol " + ID + " { \n" +
                " date=" + afficheDate() +
                ", airline=" + al.getIdentifiant() +
                ", origine=" + origine.getName() +
                ", destination=" + destination.getName() +
                ", \nsection=" + dicoSec.toString() +
                "\n}";
    }

    // Renvoie la premiere section avec des sièges libres correspondantes
    public HashSet<FlightSection> findSection(SeatClass sc)
    {
        HashSet<FlightSection> retour = new HashSet<>();
        Set clef = dicoSec.keySet();
        Iterator it = clef.iterator();
        while(it.hasNext())
        {
            Object fs = it.next();
            FlightSection tmp = dicoSec.get(fs);
            if(tmp.getSection() == sc && tmp.hasAvailableSeat())
                retour.add(tmp);
        }

        return retour;
    }
}