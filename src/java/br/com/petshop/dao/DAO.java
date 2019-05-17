package br.com.petshop.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {

    //private JPAUtil factory = new JPAUtil();  
//    private EntityManagerFactory emf
//            = Persistence.createEntityManagerFactory("frameworks");

    private final Class<T> classe;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }
    
    public void salvar(T t){
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }
    
    public void alterar(T t){
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }
    
    public void excluir(T t){
     EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(t));
        em.getTransaction().commit();
        em.close();   
    }
    
    public List<T> listaTodos() {
        //EntityManager em = factory.getEntityManager();
        EntityManager em = new JPAUtil().getEntityManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().
                createQuery(classe);
        query.select(query.from(classe));

        List<T> lista = em.createQuery(query).getResultList();
        em.close();
        return lista;
    }

    public T porId(Integer id) {
        EntityManager em = new JPAUtil().getEntityManager();
        return em.find(this.classe, id);
    }
}
