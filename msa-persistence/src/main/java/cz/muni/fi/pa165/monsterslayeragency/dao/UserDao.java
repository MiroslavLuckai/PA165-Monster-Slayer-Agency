package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
public interface UserDao {

    /***
     * Adds user to database
     * @param user user to be added
     * @throws IllegalArgumentException when user is null
     */
    void addUser(User user) throws IllegalArgumentException;

    /***
     * Updates user in database.
     * @param user user to be updated
     * @throws IllegalArgumentException when user is null
     */
    void updateUser(User user) throws IllegalArgumentException;

    /***
     * Removes user from database.
     * @param user user to be removed
     * @throws IllegalArgumentException when user is null
     */
    void removeUser(User user) throws IllegalArgumentException;

    /***
     * Finds user in database by id.
     * @param id id of user to be found
     * @return user from database with selected id
     * @throws IllegalArgumentException when id is null
     */
    User findUserById(Long id) throws IllegalArgumentException;

    /***
     * Finds user in database by email.
     * @param email email of user to be found
     * @return user from database with selected email
     * @throws IllegalArgumentException when email is null
     */
    User findUserByEmail(String email) throws IllegalArgumentException;

    /***
     * Finds user in database by username.
     * @param username username of user to be found
     * @return user from database with selected username
     * @throws IllegalArgumentException when username is null
     */
    User findUserByUsername(String username) throws IllegalArgumentException;

    /***
     * Lists all users from database.
     * @return list of all users
     */
    List<User> getAllUsers();
}
