package Elements;

import java.util.*;

/**
 * Created by Alice on 02/11/2015.
 */
public class Airline
{


    private String identifiant;
    private HashMap<String, Flight> dicoVol = new HashMap<>();
    private HashMap<String, Airport> dicoAir = new HashMap<>();
    public HashMap<String, Flight> getDicoVol() {
        return dicoVol;
    }

    // Constructeur
    public Airline(String identifiant, HashMap<String, Airport> d_airport)
    {
        this.identifiant = identifiant;
        this.dicoAir = d_airport;
    }

    //region Accesseurs
    public String getIdentifiant()
    {
        return identifiant;
    }

    //endregion

    public Flight createFlight(String ori, String dest, Calendar date, String id)
    {
        Airport origine = this.dicoAir.get(ori);
        Airport destination = this.dicoAir.get(dest);
        Flight vol = null;

        if((origine != null) && (destination != null))
        {
            vol = new Flight(id, date, this, origine, destination);
            if(dicoVol.containsKey(vol.getID()))
            {
                System.err.println("Un vol avec cet ID existe d\u00e9j\u00e0 pour cette compagnie.");
                vol = null;
            }
            else
            {
                dicoVol.put(vol.getID(), vol);
            }
        }
        if(origine == null)
        {
            System.err.println("L'aéroport d'origine n'existe pas.");
        }
        if(destination == null)
        {
            System.err.println("L'aéroport de destination n'existe pas.");
        }

        return vol;
    }

    public void createSection(String  flID, int rows,  int cols,  SeatClass  s, String id)
    {
        Flight vol = dicoVol.get(flID);
        if(vol == null)
            System.err.println("Ce vol n'existe pas.");
        else if(vol.getDicoSec().containsKey(id))
            System.err.println("Cet id existe d\u00e9j\u00e0.");
        else
            vol.createSection(rows, cols, s, id);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "identifiant='" + identifiant+
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
