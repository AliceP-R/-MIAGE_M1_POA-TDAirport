package Elements;

import java.util.*;

/**
 * Created by Alice on 02/11/2015.
 */
public class Airline
{


    private String identifiant;
    private HashSet<Flight> listeVol = new HashSet<>();
    private HashSet<Airport> listeAirport = new HashSet<>();


    // Constructeur
    public Airline(String identifiant, HashSet<Airport> l_airport)
    {
        this.identifiant = identifiant;
        this.listeAirport = l_airport;
    }

    //region Accesseurs
    public String getIdentifiant()
    {
        return identifiant;
    }

    //endregion
    public Airport findAirport(String nom)
    {
        Airport parcours;
        Iterator<Airport> it = listeAirport.iterator();
        while(it.hasNext())
        {
            parcours = (Airport)it.next();
            if(parcours.getName().equals(nom))
            {
                return parcours;
            }
        }

        return null;
    }

    public Flight createFlight(String ori, String dest, Calendar date, String id)
    {
        Airport origine = findAirport(ori);
        Airport destination = findAirport(dest);
        Flight vol = null;

        if((origine != null) && (destination != null))
        {
            vol = new Flight(id, date, this, origine, destination);
            if(listeVol.contains(vol))
            {
                System.err.println("Un vol avec cet ID existe d\u00e9j\u00e0 pour cette compagnie.");
                vol = null;
            }
            else
            {
                listeVol.add(vol);
            }
        }
        else if(origine == null)
        {
            System.err.println("L'a�roport d'origine n'existe pas.");
        }
        else if(destination == null)
        {
            System.err.println("L'a�roport de destination n'existe pas.");
        }

        return vol;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "identifiant='" + identifiant + '\'' +
                ", listeVol=" + listeVol +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        return identifiant.equals(airline.identifiant);

    }

    @Override
    public int hashCode() {
        return identifiant.hashCode();
    }
}
