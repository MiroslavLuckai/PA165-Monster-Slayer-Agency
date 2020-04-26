package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.util.List;

/**
 * @author Miroslav Luckai 469288
 */
public interface UserService {

    User registerUser(User user, String password) throws IllegalArgumentException;

    boolean authenticate(User u, String password) throws IllegalArgumentException;

    void updateUser(User user) throws IllegalArgumentException;

    void removeUser(User user) throws IllegalArgumentException;

    User findUserById(Long id) throws IllegalArgumentException;

    List<User> findAll();

    User findUserByEmail(String email) throws IllegalArgumentException;

    User findUserByUsername(String name) throws IllegalArgumentException;
}
