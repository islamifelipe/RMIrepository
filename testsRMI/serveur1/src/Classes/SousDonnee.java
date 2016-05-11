package Classes;

/**
 * Created by islamefelipefernandes on 11/05/2016.
 */
public class SousDonnee extends Donnee {

    @Override
    public void methode1() {
        System.out.println("Methode 1 d'objet SousDonnee (classe fille)");
    }

    @Override
    public void methode2() {
        System.out.println("Methode 2 d'objet SousDonnee (classe fille)");
    }

    @Override
    public String toString(){
        return "Objet SousDonnee (classe fille)";
    }
}
