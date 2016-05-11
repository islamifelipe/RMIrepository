package cliente;

import Classes.IDonnee;
import Classes.IService;
import fr.IRegistry;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by islamefelipefernandes on 21/03/2016.
 */

/*
*
* java  -Djava.rmi.server.codebase=http://Air-de-Islame-2:2001/ -Djava.security.policy="/Users/islamefelipefernandes/Desktop/ProjeReparties/registry/java.policy"   -Djava.rmi.server.useCodebaseOnly=false cliente/Client

* */
public class Client {

    public static void main(String[] args) throws RemoteException, InterruptedException, NotBoundException {

        if (System.getSecurityManager()== null){
            System.setSecurityManager(new SecurityManager());
        }
        Registry registre = LocateRegistry.getRegistry(1111);
        IRegistry serveur = (IRegistry) registre.lookup("registry");

        IService service =  (IService) serveur.lookup("helo");
        IDonnee donnee =  (IDonnee) serveur.lookup("ob2");
        IDonnee sous_donnee =  (IDonnee) serveur.lookup("ob3");



        System.out.println("En appellant le service ...");


        System.out.println("Objet = "+service);
        service.echo();
        service.echo1();

        System.out.println("Il a été appellé... ");
        System.out.println("En utilisant le donnee ...");
        donnee.methode1();
        donnee.methode2();
        System.out.println("En utilisant le sous_donnee ...");
        sous_donnee.methode1();
        sous_donnee.methode2();


        /*
        System.out.println("DECOMENTEZ POUR VOIR UNE EXCEPTION DU TYPE KEY_NOT_FOUND");
        IDonnee sous_donnee1 =  (IDonnee) serveur.lookup("NENEKFFEPOREPORPEORP");
        */
        System.out.println("C'est fini!!! :-) ");



    }


}
