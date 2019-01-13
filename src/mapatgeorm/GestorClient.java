/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapatgeorm;

import ioc.dam.m6.exemples.comandes.Client;
import ioc.dam.m6.exemples.comandes.Sector;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 * @author Ale
 * Classe encarregada de gestionar els clients
 */
public class GestorClient implements Serializable {
    
    private EntityManager em = null;
    
    /**
     * Constructor que accepta per paràmetre un EntityManager,
     * el qual s'empra per gestionar la persistència de l'aplicació
     * @param em
     */
    public GestorClient(EntityManager em) {
        this.em = em;
    }
    
    /**
     * Insereix el client passat per paràmetre a la base de dades
     * @param client
     */
    public void inserir(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }
    
    /**
     * Modifica un client que ja existeix a la base de dades
     * @param client 
     */
    public void modificar(Client client) {
        try {
            em.getTransaction().begin();
            em.merge(client);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            System.out.println("El client amb id " + client.getId() + " no existeix.");
        }
    }
    
    /**
     * Elimina un client de la base de dades mitjançant l'id d'aquest.
     * @param id 
     */
    public void eliminar(int id) {
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        if (client != null){
            em.remove(client);
            em.getTransaction().commit();
        } else {
            System.err.println("El client amb l'id " + id + " no s'ha trobat a la taula.");
        }
    }
    
    /**
     * Obté tots els clients de la base de dades i retorna una llista.
     * @return List de clients
     */
    public List<Client> obtenirClients() {
        Query q = em.createQuery("select c from Client c");
        return q.getResultList();
    }
    
    /**
     * Obté el client de la BD que té l'id passat per paràmetre.
     * @param id del client
     * @return client
     */
    public Client obtenirClient(int id) {
        return em.find(Client.class, id);
    }
    
    /**
     * Obté el client de la BD que té el nif passat per paràmetre.
     * @param nif del client
     * @return client
     */
    public Client obtenirClientPerNif(String nif) {
        Query q = em.createNamedQuery("Client.clientNif",
                Client.class);
        q.setParameter("nif", nif);
        return (Client) q.getSingleResult();
    }
    
    /**
     * Retorna una llista de clients que tenen un nom que comença o és
     * igual al nom passat per paràmetre
     * @param nom
     * @return Llista de clients
     */
    public List<Client> obtenirClientsPerNom(String nom) {
        String param = "";
        Query q = em.createNamedQuery("Client.clientsNom",
                Client.class);
        param += "%";
        param += nom;
        param += "%";
        q.setParameter("nom", param);
        return q.getResultList();
    }
    
    /**
     * Retorna una llista de clients que pertanyen al sector passat per paràmetre.
     * @param sector
     * @return Llista de clients del sector.
     */
    public List<Client> obtenirClientsPerSector(Sector sector) {
        Query q = em.createNamedQuery("Client.clientsSector",
                Client.class);
        q.setParameter("sector", sector.getId());
        return q.getResultList();
    }
}
