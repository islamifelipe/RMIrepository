package Classes;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by islamefelipefernandes on 21/03/2016.
 */
public interface IService extends Remote {
    void echo() throws RemoteException, InterruptedException;
    void echo1() throws RemoteException, InterruptedException;


}
