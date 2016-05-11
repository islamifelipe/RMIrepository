package Classes;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * Created by islamefelipefernandes on 11/05/2016.
 *
 * Ceci sera l'interface d'un objet qui sera serialisé.
 * Donc, c'est-à-dire que ce objet sera partagé par un serveur et qui poura être utilisé par plusieurs clients
 * Cette interface ne sera pas implmentée par un service RMI : pas un objet Distant. Donc, l'execution des ses methodes sera executée chez le client
 *
 */
public interface IDonnee extends Remote, Serializable{
    void methode1();
    void methode2();
}
