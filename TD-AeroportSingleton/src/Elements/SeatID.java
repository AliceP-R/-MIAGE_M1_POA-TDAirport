package Elements;

/**
 * Created by Alice on 02/11/2015.
 */
public class SeatID
{
    private int ligne;
    private char colonne;

    public SeatID(int ligne, char colonne)
    {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @Override
    public String toString() {
        return ""+ligne+""+colonne;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatID seatID = (SeatID) o;

        if (ligne != seatID.ligne) return false;
        return colonne == seatID.colonne;

    }

    @Override
    public int hashCode() {
        int result = ligne;
        result = 31 * result + (int) colonne;
        return result;
    }
}
