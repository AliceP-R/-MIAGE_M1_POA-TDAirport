package Elements;

/**
 * Created by Alice on 02/11/2015.
 */
public class Seat
{
    private SeatID seatNum;
    private boolean isBooked;

    public Seat(SeatID seatNum)
    {
        this.seatNum = seatNum;
        this.isBooked = false;
    }


    public SeatID getSeatNum()
    {
        return seatNum;
    }

    public boolean getStatus()
    {
        return isBooked;
    }

    public void booked()
    {
        this.isBooked = true;
    }

    @Override
    public String toString() {
        String resa;
        if(this.isBooked)
            resa = "r\u00e9serv\u00e9";
        else
            resa = "libre";
        return "" + seatNum + ", "+resa;
    }
}
