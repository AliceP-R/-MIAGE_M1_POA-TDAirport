package Elements;

/**
 * Created by Alice on 02/11/2015.
 *
 * Cette classe définit une section tarifaire.
 * Chaque section possède une Seatclass et au moins 1 siège.
 * Une FlightSection possède les attributs nombre de rows (ligne) et nombre de columns (colonne),
 * afin de savoir combien de sièges elle contient et le calcul du nombre de sièges disponibles.
 */

public class FlightSection
{
    private SeatClass section;
    private int ligne;
    private int colonne;

    // Constructeur
    public FlightSection(SeatClass section, int ligne, int colonne) {
        this.section = section;
        this.ligne = ligne;
        this.colonne = colonne;
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
        return true;
    }

    public Boolean bookSeat()
    {
        return true;
    }



}
