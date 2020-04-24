package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.UserDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.getAllUsers();
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserByUsername(String name) {
        return userDao.findUserByUsername(name);
    }
}
