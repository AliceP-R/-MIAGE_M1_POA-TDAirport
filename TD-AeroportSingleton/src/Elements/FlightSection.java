package Elements;

import SystemManager.SystemManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
    private String identifiant;
    private SeatClass section;
    private int ligne;
    private int colonne;
    private HashMap<SeatID, Seat> dicoSiege;


    public String getIdentifiant()
    {
        return identifiant;
    }

    // Constructeur
    public FlightSection(String id, SeatClass section, int ligne, int colonne) {
        this.identifiant = id;
        this.section = section;
        this.ligne = ligne;
        this.colonne = colonne;
        this.dicoSiege = new HashMap<>();

        for(int i=0; i<ligne; i++)
        {
            char c = 'A';
            for(int j=0; j<colonne;j++)
            {
                SeatID st = new SeatID(i, c);
                c++;
                Seat s = new Seat(st);
                dicoSiege.put(s.getSeatNum(), s);
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
        Set<SeatID> clef = dicoSiege.keySet();
        Iterator it = clef.iterator();
        while(it.hasNext())
        {
            // si il existe au moins un si�ge libre (isBooked = false), on retourne true = il reste des places
            if(dicoSiege.get(it.next()).getStatus()==false)
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
            Set<SeatID> clef = dicoSiege.keySet();
            Iterator it = clef.iterator();
            // tant qu'aucun si�ge n'a �t� reserv�
            while(res == false && it.hasNext())
            {
                Seat s = dicoSiege.get(it.next());
                // si le si�ge est libre (isBooked = false), on le booked
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
        boolean res = false;
        Seat siege = dicoSiege.get(sid);

        if(siege != null && siege.getStatus() == false)
        {
            siege.booked();
            res = true;
        }
        else if(siege != null)
            System.err.println("Ce si�ge est d�j� r�serv�.");
        else
            System.err.println("Ce si�ge n'existe pas.");



        return res;
    }

    @Override
    public String toString() {
        return "FlightSection{" +
                "identifiant='" + identifiant + '\'' +
                ", section=" + section +
                ", ligne=" + ligne +
                ", colonne=" + colonne +
                ", dicoSiege=" + dicoSiege +
                '}';
    }
}
