package fr;

import fr.Exceptions.KeyAlreadyUsed;
import fr.Exceptions.KeyNotFound;

import java.rmi.AccessException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by islamefelipefernandes on 10/05/2016.
 */
public interface IRegistry extends Remote {
    boolean rebind(String nom, Remote obj) throws RemoteException, InterruptedException, KeyAlreadyUsed;;
    Remote lookup(String nom) throws RemoteException, InterruptedException, KeyNotFound;;
    String[] list() throws RemoteException, InterruptedException;
    String[] derniersEnregistrement(int x) throws RemoteException, InterruptedException;
    String[] plusDemande(int x) throws RemoteException, InterruptedException;

}
