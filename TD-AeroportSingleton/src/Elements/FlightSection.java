package Elements;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Alice on 02/11/2015.
 *
 * Cette classe d�finit une section tarifaire.
 * Chaque section poss�de une Seatclass et au moins 1 si�ge.
 * Une FlightSection poss�de les attributs nombre de rows (ligne) et nombre de columns (colonne),
 * afin de savoir combien de si�ges elle contient et le calcul du nombre de si�ges disponibles.
 */

public class FlightSection
{
    private SeatClass section;
    private int ligne;
    private int colonne;
    private HashSet<Seat> l_siege;

    // Constructeur
    public FlightSection(SeatClass section, int ligne, int colonne) {
        this.section = section;
        this.ligne = ligne;
        this.colonne = colonne;
        this.l_siege = new HashSet<>();

        for(int i=0; i<ligne; i++)
        {
            char c = 'A';
            for(int j=0; j<colonne;j++)
            {
                SeatID st = new SeatID(i, c);
                c++;
                Seat s = new Seat(st);
                l_siege.add(s);
            }
        }
    }

    //region Accesseurs
    public SeatClass getSection()
    {
        return section;
    }

    public void setSection(SeatClass section)
    {
        this.section = section;
    }
    //endregion

    public Boolean hasAvailableSeat()
    {
        Iterator it = l_siege.iterator();
        while(it.hasNext())
        {
            // si il existe au moins un si�ge libre (isBooked = false), on retourne true = il reste des places
            if(((Seat)it.next()).getStatus()==false)
            {
                return true;
            }
        }
        return false;
    }

    public Boolean bookSeat()
    {
        boolean res = false;
        if(hasAvailableSeat())
        {
            Iterator it = l_siege.iterator();
            while(it.hasNext() && res == false)
            {
                Seat s = (Seat)it.next();
                // si il existe au moins un si�ge libre (isBooked = false), on retourne true = il reste des places
                if(s.getStatus()==false)
                {
                    s.booked();
                    res = true;
                }
            }
        }
        else
            System.err.println("Il n'y a aucun si�ge disponible dans cette section.");

        return res;
    }

    public Boolean bookSeat(SeatID sid)
    {
        Boolean res = false;
        Iterator it = l_siege.iterator();
        while(it.hasNext())
        {
            Seat s = (Seat)it.next();
            // si il existe au moins un si�ge libre (isBooked = false), on retourne true = il reste des places
            if(s.getSeatNum() == sid)
            {
                if(s.getStatus()==false)
                {
                    s.booked();
                    res = true;
                }
                else
                    System.err.println("Ce si�ge n'est pas disponible.");
            }
            else
                System.err.println("Ce si�ge n'existe pas.");

        }

        return res;
    }



}
