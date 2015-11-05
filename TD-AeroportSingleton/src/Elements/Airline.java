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


    public Airline(String identifiant, HashSet<Airport> l_airport)
    {
        this.identifiant = identifiant;
        this.listeAirport = l_airport;
    }
    public String getIdentifiant() {
        return identifiant;
    }

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

        if((origine != null) && (destination != null))
            return new Flight(id, date, this, origine, destination);
        else
            return null;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "identifiant='" + identifiant + '\'' +
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
