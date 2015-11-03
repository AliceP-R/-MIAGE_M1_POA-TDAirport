package Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alice on 02/11/2015.
 */
public class Airline
{
    private String identifiant;
    private List<Flight> listeVol = new ArrayList<Flight>();


    public Airline(String identifiant) {
        this.identifiant = identifiant;
    }
}
