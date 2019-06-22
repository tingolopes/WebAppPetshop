package br.com.petshop.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import br.com.petshop.model.EntidadeBase;

public class DAO<T extends EntidadeBase> {

    private final Class<T> classe;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void saveOrUpdate(T t) {
        EntityManager em = new JPAUtil().getEntityManager();
        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t);
            } else {
                em.merge(t);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    public void excluir(T t) {
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
