package fr.ObjetDistants;
/*java  -Djava.rmi.server.codebase=http://Air-de-Islame-2:2001/ -Djava.security.policy="/Users/islamefelipefernandes/Desktop/ProjeReparties/registry/java.policy"   -Djava.rmi.server.useCodebaseOnly=false fr/serveur/Serveur
k*/
import fr.Exceptions.KeyAlreadyUsed;
import fr.Exceptions.KeyNotFound;
import fr.IRegistry;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by islamefelipefernandes on 10/05/2016.
 */
public class RegistryImpl extends UnicastRemoteObject implements IRegistry {
    private Hashtable<String, Remote> mapRegistre;
    private Hashtable<String, Integer> mapOrdeneParDemande;



    public RegistryImpl() throws RemoteException {
       mapRegistre = new Hashtable(101);
        mapOrdeneParDemande = new Hashtable(101);



    }

    @Override
    public boolean rebind(String nom, Remote obj) throws RemoteException, InterruptedException, KeyAlreadyUsed {

        synchronized(this.mapRegistre) {
            if (mapRegistre.containsKey(nom)) {
                System.out.println("ERROR : Objet déjà enregistré !!!!");
                throw new KeyAlreadyUsed(nom);

            }
            mapRegistre.put(nom, obj);
            mapOrdeneParDemande.put(nom, 0);
            System.out.println("Enregistré!!!! + "+mapRegistre.size());
            return true;
        }
    }

    @Override
    public Remote lookup(String nom) throws RemoteException, InterruptedException, KeyNotFound {

        synchronized(this.mapRegistre) {
            if (mapRegistre.containsKey(nom)) {
                System.out.println("Demandée : " + mapRegistre.get(nom));
                mapOrdeneParDemande.put(nom, mapOrdeneParDemande.get(nom) + 1);
                return mapRegistre.get(nom);
            } else {

                System.out.println("ERROR : Clée "+ nom +" non enregistrée !!!! ");
                list();
                throw  new KeyNotFound(nom);
            }
        }

    }

    @Override
    public String[] list() throws RemoteException, InterruptedException {
        synchronized(this.mapRegistre) {
            int taille = this.mapRegistre.size();
            String[] ret = new String[taille];
            Enumeration chaves = this.mapRegistre.keys();

            for (int i = 0; i < taille; i++) {
                ret[i] = (String) chaves.nextElement();
                System.out.println(ret[i]+ ", ");
            }
            return ret;

        }
    }

    @Override
    public String[] derniersEnregistrement(int x) throws RemoteException, InterruptedException {
        synchronized(this.mapRegistre) {
            int taille = this.mapRegistre.size()<x? this.mapRegistre.size() : x;

            String[] ret = new String[x];
            Enumeration chaves = this.mapRegistre.keys();

            for (int i = 0; i < taille && x>0; i++) {
                ret[i] = (String) chaves.nextElement();
                x--;

            }
            return ret;

        }
    }

    @Override
    public String[] plusDemande(int x) throws RemoteException, InterruptedException {
        synchronized(this.mapRegistre) {
            ValueComparator comparator = new ValueComparator(mapOrdeneParDemande);
            int taille = this.mapOrdeneParDemande.size()<x? this.mapOrdeneParDemande.size() : x;
            TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(comparator);
            sorted_map.putAll(mapOrdeneParDemande);
            String[] ret = new String[taille];
            Set<String> chaves = sorted_map.keySet();

            int i  = 0;
            for (Iterator<String> it = chaves.iterator(); it.hasNext() && x>0; ){
                ret[i++] = it.next();
                x--;
            }
            return ret;

        }
    }


    private class ValueComparator implements Comparator<String> {
        Hashtable<String, Integer> base;

        public ValueComparator(Hashtable<String, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with
        // equals.
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }

}
