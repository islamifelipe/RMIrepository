package fr;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by islamefelipefernandes on 10/05/2016.
 */
public interface IRegistry extends Remote {
    boolean rebind(String nom, Remote obj) throws RemoteException, InterruptedException;;
    Remote lookup(String nom) throws RemoteException, InterruptedException;;

}
