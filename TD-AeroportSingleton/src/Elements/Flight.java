package Elements;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

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
    private HashSet<FlightSection> l_section;

    public Flight(String ID, Calendar date, Airline al, Airport origine, Airport destination) {
        this.ID = ID;
        this.date = date;
        this.al = al;
        this.origine = origine;
        this.destination = destination;
        this.l_section = new HashSet<>();
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
        if(l_section.isEmpty())
            return  false;

        return true;
    }

    // renvoie true si il reste des sièges disponibles pour ce vol
    public boolean hasSeats()
    {
        boolean res=false;
        if(hasSection())
        {
            Iterator it = l_section.iterator();
            while (it.hasNext())
            {
                FlightSection fs = (FlightSection)it.next();
                if(fs.hasAvailableSeat())
                {
                    res=true;
                }
            }
        }
        return res;
    }

    // si le nombre de rangé et le nombre de siège par ranger est correct, créé une nouvelle section tarifaire pour ce vol
    public boolean createSection(int rows, int cols, SeatClass s)
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
            l_section.add(new FlightSection(s, rows, cols));
        }

        return res;
    }


    public boolean bookSeat()
    {
        boolean res=false;
        if(this.hasSeats() && res==false)
        {
            Iterator it_fs = l_section.iterator();
            while(it_fs.hasNext())
            {
                FlightSection etude = (FlightSection) it_fs.next();
                res = etude.bookSeat();
            }
        }

        return res;
    }
/*
    +

    +createSection(): Boolean
    +?ndSection(): FlightSection
    +bookSeat()
    */

}
