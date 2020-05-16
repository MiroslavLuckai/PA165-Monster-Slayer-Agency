package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.AuthenticationResponseDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.dto.UserRegistrationDTO;

import java.util.List;

public interface UserFacade {

    /**
     * Creates new User in database
     * @param registrationDTO user to be created and password for account
     * @return newly created User
     */
    UserDTO registerUser(UserRegistrationDTO registrationDTO);

    /**
     * Compare saved user credentials with one entered
     * @param email email of the user to be authenticated
     * @param password entered password
     * @return result of authentication
     */
    AuthenticationResponseDTO authenticateUser(String email, String password);

    /***
     * Updates user in database.
     * @param user user to be updated
     */
    void updateUser(UserDTO user);

    /***
     * Removes user from database.
     * @param id id of the user to be removed
     */
    void removeUser(Long id);

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
