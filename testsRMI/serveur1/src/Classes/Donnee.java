package Classes;

/**
 * Created by islamefelipefernandes on 11/05/2016.
 */
public class Donnee implements IDonnee {
    @Override
    public void methode1() {
        System.out.println("Methode 1 d'objet Donnee (classe mère)");
    }

    @Override
    public void methode2() {
        System.out.println("Methode 2 d'objet Donnee (classe mère)");
    }

    @Override
    public String toString(){
        return "Objet Donnee (classe mère)";
    }

}
