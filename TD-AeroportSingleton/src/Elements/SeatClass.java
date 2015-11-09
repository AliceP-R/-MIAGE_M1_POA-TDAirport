package Elements;

/**
 * Created by Alice on 02/11/2015.
 */
public enum SeatClass
{
    FIRST("First"),
    BUSI("Business"),
    ECO("Economic");

    private final String nom;

    SeatClass(String nom)
    {
        this.nom = nom;
    }
}
