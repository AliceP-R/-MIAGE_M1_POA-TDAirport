package MesExceptions;

/**
 * Created by Alice on 12/11/2015.
 */
public class lenghtException extends Throwable {
    public lenghtException(int taille){
        System.out.println("Cet ID n'est pas de taille "+taille);
    }
}
