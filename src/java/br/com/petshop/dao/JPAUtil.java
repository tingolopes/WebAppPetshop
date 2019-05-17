package br.com.petshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf = 
    Persistence.createEntityManagerFactory("frameworks");
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void close(EntityManager em){
        em.close();
    }
}
