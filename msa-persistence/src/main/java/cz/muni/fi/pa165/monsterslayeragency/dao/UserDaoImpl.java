package cz.muni.fi.pa165.monsterslayeragency.dao;

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
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.persist(user);
    }

    @Override
    public void updateUser(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.merge(user);
    }

    @Override
    public void removeUser(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.remove(user);
    }

    @Override
    public User findUserById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("User ID is null");
        }
        return em.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) throws IllegalArgumentException {
        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }
        try {
            return em.createQuery("select usr from User usr where usr.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public User findUserByUsername(String username) throws IllegalArgumentException {
        if (username == null) {
            throw new IllegalArgumentException("Username is null");
        }
        try {
            return em.createQuery("select usr from User usr where usr.userName = :userName", User.class)
                    .setParameter("userName", username)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select usr from User usr", User.class)
                .getResultList();
    }
}
