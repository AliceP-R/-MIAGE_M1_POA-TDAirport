package MesExceptions;

/**
 * Created by Alice on 12/11/2015.
 */
public class doublonException extends Exception {
    public doublonException(){
        System.out.println("Cet ID existe déjà !");
    }
}
