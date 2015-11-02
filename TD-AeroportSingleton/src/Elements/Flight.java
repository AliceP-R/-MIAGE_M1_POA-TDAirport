package Elements;

import java.util.Calendar;

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

    public Flight(String ID, Calendar date, Airline al, Airport origine, Airport destination) {
        this.ID = ID;
        this.date = date;
        this.al = al;
        this.origine = origine;
        this.destination = destination;
    }

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



/*
    +hasSection():Boolean
    +hasSeats(): Boolean
    +createSection(): Boolean
    +?ndSection(): FlightSection
    +bookSeat()
    */

}
