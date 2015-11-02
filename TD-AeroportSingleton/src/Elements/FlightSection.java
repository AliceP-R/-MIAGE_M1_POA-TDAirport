package Elements;

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
