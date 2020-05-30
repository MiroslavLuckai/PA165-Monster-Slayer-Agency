package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.AuthenticationResponseDTO;
import cz.muni.fi.pa165.msa.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.dto.UserRegistrationDTO;
import cz.muni.fi.pa165.msa.facade.UserFacade;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    /**
     * Get list of users stored in database
     * curl -i -X GET http://localhost:8080/pa165/rest/users
     * @return list of users stored in database
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() {
        try {
            logger.debug("Get all users");
            return userFacade.getAllUsers();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Cannot find User." + ex.getMessage(), ex);
        }
    }

    /**
     * Get user by his id
     * curl -i -X GET http://localhost:8080/pa165/rest/users/{id}
     * @param id id of the user we are looking for
     * @return UserDTO we are looking for, exception if user with given id does not exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserById(@PathVariable("id") Long id) {
        logger.debug("Find user({})", id);
        UserDTO user = userFacade.findUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("Cannot find User with the given ID.");
        }
        return user;
    }

    /**
     * Get user by his email
     * @param email email of the user we are looking for
     * @return UserDTO we are looking for, exception is thrown if user with given email does not exist
     */
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByEmail(@PathVariable("email") String email) {

        logger.debug("Find user({})", email);
        UserDTO user = userFacade.findUserByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Cannot find User with the given email.");
        }
        return user;
    }

    /**
     * Get user by his username
     * @param username username of the user we are looking for
     * @return UserDTO of the user we are looking for, exception if user with given username does not exist
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByUsername(@PathVariable("username") String username) {
        logger.debug("Find user({})", username);
        UserDTO user = userFacade.findUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("Cannot find User with the given name.");
        }
        return user;
    }

    /**
     * Authenticate user
     * @param dto AuthenticationDTO containing email and password of the user who want to be authenticated
     * @return Object of the User and information if auth succeeded, null if it did not
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AuthenticationResponseDTO authenticateUser(@RequestBody UserAuthenticationDTO dto) {
        try {
            logger.debug("Authenticating user with email: {}", dto.getEmail());
            return userFacade.authenticateUser(dto.getEmail(), dto.getPassword());
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Cannot authenticate user." + ex.getMessage(), ex);
        }
    }

    /**
     * Remove user from database
     * @param id id of the user we want to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void removeUser(@PathVariable Long id) {
        try {
            logger.debug("Removing user with id: {}", id);

            userFacade.removeUser(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Cannot remove user with the giver ID." + ex.getMessage(), ex);
        }
    }

    /**
     * Register new user into application
     * @param user user we are registering and his password
     * @return newly created user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO registerUser(@RequestBody UserRegistrationDTO user) {
        return userFacade.registerUser(user);
    }

}
