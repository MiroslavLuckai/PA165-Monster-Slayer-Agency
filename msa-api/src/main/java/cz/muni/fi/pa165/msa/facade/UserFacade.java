package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.UserDTO;

import java.util.List;

public interface UserFacade {

    /**
     * Creates new User in database
     * @param userDTO user to be created
     * @return id of the newly created User
     */
    Long createUser(UserDTO userDTO);

    /***
     * Updates user in database.
     * @param userDTO user to be updated
     */
    void updateUser(UserDTO userDTO);

    /***
     * Removes user from database.
     * @param userDTO user to be removed
     */
    void removeUser(UserDTO userDTO);

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
