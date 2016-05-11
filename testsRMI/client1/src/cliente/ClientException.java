package cliente;

import Classes.IDonnee;
import Classes.IService;
import fr.IRegistry;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by islamefelipefernandes on 11/05/2016.
 */

/*
* Ce client il apelle une clée du registre que n'existe pas. Ca donne une exception du type KeyNotFound
* **/


    /*
    java  -Djava.rmi.server.codebase=http://Air-de-Islame-2:2001/ -Djava.security.policy="/Users/islamefelipefernandes/Desktop/ProjeReparties/registry/java.policy"   -Djava.rmi.server.useCodebaseOnly=false cliente/ClientException
    * */
public class ClientException {
    public static void main(String[] args) throws RemoteException, InterruptedException, NotBoundException {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Registry registre = LocateRegistry.getRegistry(1111);
        IRegistry serveur = (IRegistry) registre.lookup("registry");

        IDonnee sous_donnee = (IDonnee) serveur.lookup("ob3");

        System.out.println("En utilisant le sous_donnee ...");
        sous_donnee.methode1();
        sous_donnee.methode2();


        System.out.println("VOUS VERREZ UNE EXCEPTION DU TYPE KEY_NOT_FOUND");
        IDonnee sous_donnee1 =  (IDonnee) serveur.lookup("NENEKFFEPOREPORPEORP"); //// BOOOOOOOOOOOOOOOOOOOOONNNNNNNNNWWWWW

        System.out.println("C'est fini!!! :-) ");


    }
}
