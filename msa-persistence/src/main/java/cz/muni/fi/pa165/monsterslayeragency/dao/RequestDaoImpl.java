package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RequestDaoImpl implements RequestDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addRequest(Request request) {
        if (request != null) {
            em.persist(request);
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override
    public void removeRequest(Request request) {
        if (request != null) {
            em.remove(request);
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override
    public void updateRequest(Request request) {
        if (request != null) {
            em.merge(request);
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override
    public Request findRequestById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return em.find(Request.class, id);
    }

    @Override
    public Request findRequestByCustomer(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        try {
            return em.createQuery("select req from Request req where req.customer = :user", Request.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Request> findAll() {
        return em.createQuery("select req from Request req", Request.class)
                .getResultList();
    }
}
