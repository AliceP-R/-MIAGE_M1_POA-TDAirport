package Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alice on 02/11/2015.
 */
public class Airline
{
    private int identifiant;
    private List<Flight> listeVol = new ArrayList<Flight>();

    // : cr´ee un vol pour une compagnie a´erienne.
    public Flight  createFlight(String  orig,  String  dest,  Calendar date,  String  id)
    {
        return new Flight();
    }
}
