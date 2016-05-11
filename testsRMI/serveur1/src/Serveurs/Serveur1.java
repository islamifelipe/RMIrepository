package Serveurs;

import Classes.Donnee;
import Classes.Service;
import Classes.SousDonnee;
import fr.*;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by islamefelipefernandes on 21/03/2016.
 */

/*
* java  -Djava.rmi.server.codebase=http://Air-de-Islame-2:2001/ -Djava.security.policy="/Users/islamefelipefernandes/Dies/registry/java.policy"   -Djava.rmi.server.useCodebaseOnly=false Serveurs/Serveur1

*/
public class Serveur1 {


    public static void main(String[] args) throws RemoteException, MalformedURLException, IllegalAccessException, InstantiationException, NotBoundException { // registar o objeto



            if (System.getSecurityManager()== null){
                System.setSecurityManager(new SecurityManager());
            }

            Registry registre = LocateRegistry.getRegistry(1111);
            IRegistry serveur = (IRegistry) registre.lookup("registry");

           Runnable fornece = new Runnable() {
                public void run(){

                    try {
                        Service od = new Service();
                        Donnee ob2 = new Donnee();
                        SousDonnee ob3 = new SousDonnee();

                        serveur.rebind("helo",od); // service
                        serveur.rebind("ob2",ob2); // Donnee
                        serveur.rebind("ob3",ob3); // Sous Donnee


                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            };

            //serveur.rebind("ob3",ob3); // Si vous le decomentez, il genera une exception du type KeyAlreadyUsed, que l'on a cree pour signaler une clée double


        Runnable statisques = new Runnable() {
            public void run(){
                try {
                    Scanner sc = new Scanner(System.in);
                    do {
                        System.out.print("\nMenu : \n (1) - Savoir les X derniers enregistremaint (de ce serveur et de n'importe quel d'autre) \n " +
                                "(2) - Savoir les X clée plus demandées par les clients \n " +
                                "(3) - Demander la liste de toutes les clées. \n " +
                                "Votre option = ");
                        int op = sc.nextInt();
                        //System.out.println(op);
                        switch (op) {
                            case 1 :
                                System.out.print("Valeur de X = ");
                                int x = sc.nextInt();
                                String[] derniers = serveur.derniersEnregistrement(x);
                                for (int i = 0; i < derniers.length; i++) {
                                    System.out.print(derniers[i] + ", ");
                                }
                                break;
                            case 2:
                                System.out.print("Valeur de X = ");
                                int xx = sc.nextInt();
                                String[] plus = serveur.plusDemande(xx);
                                for (int i = 0; i < plus.length; i++) {
                                    System.out.print(plus[i] + ", ");
                                }
                                break;
                            case 3:
                                String[] list = serveur.list();
                                for (int i = 0; i < list.length; i++) {
                                    System.out.print(list[i] + ", ");
                                }
                                break;
                            default:
                                System.out.println("Option inconue");
                        }
                    } while (true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        new Thread(fornece).start();
        new Thread(statisques).start();


    }
}
