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
        validate(user, "User cannot be null!");
        validate(user.getEmail(), "User email cannot be null!");
        validate(user.getUserName(), "Username cannot be null!");
        validate(user.getPassword(), "User password cannot be null!");
        em.persist(user);
    }

    @Override
    public void updateUser(User user) throws IllegalArgumentException {
        validate(user, "User cannot be null!");
        em.merge(user);
    }

    @Override
    public void removeUser(User user) throws IllegalArgumentException {
        validate(user, "User cannot be null!");
        em.remove(user);
    }

    @Override
    public User findUserById(Long id) throws IllegalArgumentException {
        validate(id, "ID cannot be null!");
        return em.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) throws IllegalArgumentException {
        validate(email, "Email cannot be null!");
        List<User> users = em.createQuery("select usr from User usr where usr.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findUserByUsername(String username) throws IllegalArgumentException {
        validate(username, "Username cannot be null!");
        List<User> users = em.createQuery("select usr from User usr where usr.userName = :userName", User.class)
                .setParameter("userName", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select usr from User usr", User.class)
                .getResultList();
    }

    private void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
