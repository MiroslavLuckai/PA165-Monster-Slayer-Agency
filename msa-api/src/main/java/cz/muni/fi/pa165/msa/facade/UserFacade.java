package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.UserDTO;

import java.util.List;

public interface UserFacade {

    Long createUser(UserDTO user);

    /***
     * Updates user in database.
     * @param user user to be updated
     */
    void updateUser(UserDTO user);

    /***
     * Removes user from database.
     * @param user user to be removed
     */
    void removeUser(UserDTO user);

    /***
     * Finds user in database by id.
     * @param id id of user to be found
     * @return user from database with selected id
     */
    UserDTO findUserById(Long id);

    /***
     * Finds user in database by email.
     * @param email email of user to be found
     * @return user from database with selected email
     */
    UserDTO findUserByEmail(String email);

    /***
     * Finds user in database by username.
     * @param username username of user to be found
     * @return user from database with selected username
     */
    UserDTO findUserByUsername(String username);

    /***
     * Lists all users from database.
     * @return list of all users
     */
    List<UserDTO> getAllUsers();
}
