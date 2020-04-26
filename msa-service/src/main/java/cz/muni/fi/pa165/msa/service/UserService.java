package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miroslav Luckai 469288
 */
public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void removeUser(User user);

    User findUserById(Long id);

    List<User> findAll();

    User findUserByEmail(String email);

    User findUserByUsername(String name);
}
