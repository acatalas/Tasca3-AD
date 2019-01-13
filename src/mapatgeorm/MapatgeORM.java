/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapatgeorm;

import ioc.dam.m6.exemples.comandes.Client;
import ioc.dam.m6.exemples.comandes.Sector;
import ioc.dam.m6.exemples.comandes.Zona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ale
 */
public class MapatgeORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Cream la fàbrica que necessitarem per crear l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MapatgeORMPU");
        
        //Cream l'EntityManager
        EntityManager em = emf.createEntityManager();
        
        //Passam l'EntityManager al gestor de clients
        GestorClient gestorClient = new GestorClient(em);
        
        //Inserir un client
        /*Cream el sector i la zona del client
        Sector sector = new Sector("1", "Sector 1");
        Zona zona = new Zona("1", "Zona 1");
        
        //Inserim el sector i la zona del client
        em.getTransaction().begin();
        em.persist(sector);
        em.persist(zona);
        em.getTransaction().commit();
        
        //Cream el client
        Client client = new Client("41746514L", "Maria Anglada");
        client.setSector(sector);
        client.setZona(zona);
        
        //Inserim el client
        //gestorClient.inserir(client);*/
        
        //Modificar un client
        /*Client client = new Client("41747291L", "Pep Pons");
        gestorClient.inserir(client);
        client.setNom("Toni Pons");
        gestorClient.modificar(client);*/
        
        //Eliminar un client
        /*gestorClient.eliminar(1000);*/
        
        //Obtenir 1 client
        /*System.out.println(gestorClient.obtenirClient(13));*/
       
        //Obtenir clients
        //Cream una zona i un sector nous
        /*Sector sector2 = new Sector("2", "Sector 2");
        Zona zona2 = new Zona("2", "Zona 2");
        em.getTransaction().begin();
        em.persist(sector2);
        em.persist(zona2);
        em.getTransaction().commit();*/
        
        //Recuperam totes les zones i sectors.
        /*Zona zona1 = em.find(Zona.class, "1");
        Zona zona2 = em.find(Zona.class, "2");
        Sector sector1 = em.find(Sector.class, "1");
        Sector sector2 = em.find(Sector.class, "2");
        
        //Cream clients
        Client client1 = new Client("47385902P", "Jaume Bibiloni");
        client1.setSector(sector2);
        client1.setZona(zona2);
        
        Client client2 = new Client("83749208P", "Pere Marques");
        client2.setSector(sector1);
        client2.setZona(zona2);
        
        Client client3 = new Client("53748129L", "Francesc Moll");
        client3.setSector(sector2);
        client3.setZona(zona1);
        
        //Guardam els clients a la taula
        em.getTransaction().begin();
        em.persist(client1);
        em.persist(client2);
        em.persist(client3);
        em.getTransaction().commit();*/
        
        //Obtenir tots els clients
        /*List<Client> clients = gestorClient.obtenirClients();
        for(Client client : clients){
            System.out.println(client);
        }*/
        
        //Obtenir un client a partir del nif
        /*System.out.println(gestorClient.obtenirClientPerNif("83749208P"));*/
        
        //Obtenir clients per nom
        /*List<Client> clients = gestorClient.obtenirClientsPerNom("hola");
        for (Client client : clients){
            System.out.println(client);
        }*/
        
        //Obtenir clients per sector
        //Recuperam el sector
        Sector sector = em.find(Sector.class, "3");
        List<Client> clients = gestorClient.obtenirClientsPerSector(sector);
        for (Client client : clients){
            System.out.println(client);
        }
    }
    
}
