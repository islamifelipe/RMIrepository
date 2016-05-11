package Classes;

import java.rmi.RemoteException;

/**
 * Created by islamefelipefernandes on 10/05/2016.
 */
public interface IService1 extends IService {
    void echo1() throws RemoteException, InterruptedException;

}
