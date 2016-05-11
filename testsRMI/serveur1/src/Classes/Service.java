package Classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by islamefelipefernandes on 21/03/2016.
 */
public class Service extends UnicastRemoteObject implements IService1 {

     // UnicastRemoteObject é para que a classe fique sempre escutando...
     public Service() throws RemoteException {
    }

    @Override
    public void echo() throws RemoteException, InterruptedException {
        Thread.sleep(10);
        System.out.println("Menssagem 0000");
    }

    @Override
    public String toString(){
        return "AQQQQQQQQQQQUUUUUI";
    }

    @Override
    public void echo1() throws RemoteException, InterruptedException {
        Thread.sleep(10);
        System.out.println("Menssagem 11111");
    }
}

