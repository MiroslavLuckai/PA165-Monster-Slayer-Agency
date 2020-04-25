package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Filip Daniel Fedin
 */

@Repository
@Transactional
public class RequestDaoImpl implements RequestDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addRequest(Request request) throws IllegalArgumentException{
        validate(request, "Request cannot be null!");
        em.persist(request);
    }

    @Override
    public void removeRequest(Request request) throws IllegalArgumentException{
        validate(request, "Request cannot be null!");
        em.remove(request);
    }

    @Override
    public void updateRequest(Request request) throws IllegalArgumentException{
        validate(request, "Request cannot be null!");
        em.merge(request);
    }

    @Override
    public Request findRequestById(Long id) throws IllegalArgumentException{
        validate(id, "ID cannot be null!");
        return em.find(Request.class, id);
    }

    @Override
    public Request findRequestByCustomer(User user) throws IllegalArgumentException{
        validate(user, "User cannot be null!");
        return em.createQuery("select req from Request req where req.customer = :user", Request.class)
                .setParameter("user", user)
                .getSingleResult();
    }

    @Override
    public List<Request> findAll() {
        return em.createQuery("select req from Request req", Request.class)
                .getResultList();
    }

    private void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
