package fr.serveur;


import fr.ObjetDistants.RegistryImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
* rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 1111 &

*
*
* java  -Djava.rmi.server.codebase=http://Air-de-Islame-2:2001/ -Djava.security.policy="/Users/islamefelipefernandes/Desktop/ProjeRepaies/registry/java.policy"   -Djava.rmi.server.useCodebaseOnly=false fr/serveur/Serveur
* */

/**
 * Created by islamefelipefernandes on 11/05/2016.
 */
public class Serveur {

    public static void main(String[] args) throws RemoteException, MalformedURLException, IllegalAccessException, InstantiationException  {

         final int PORTE_REGISTRE_UNIVERSEL = 1111;
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            Registry registre = LocateRegistry.getRegistry(PORTE_REGISTRE_UNIVERSEL);


           RegistryImpl od = new RegistryImpl();

            registre.rebind("registry", od);


    }
}
