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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() {
        try {
            logger.debug("Get all users");
            return userFacade.getAllUsers();
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserById(@PathVariable("id") Long id) {
        try {
            logger.debug("Find user({})", id);
            return userFacade.findUserById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByEmail(@PathVariable("email") String email) {
        try {
            logger.debug("Find user({})", email);
            return userFacade.findUserByEmail(email);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByUsername(@PathVariable("username") String username) {
        try {
            logger.debug("Find user({})", username);
            return userFacade.findUserByUsername(username);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AuthenticationResponseDTO authenticateUser(@RequestBody UserAuthenticationDTO dto) {
        try {
            logger.debug("Authenticating user with email: {}", dto.getEmail());
            return userFacade.authenticateUser(dto.getEmail(), dto.getPassword());
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void removeUser(@PathVariable Long id) {
        try {
            logger.debug("Removing user with id: {}", id);

            userFacade.removeUser(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO registerUser(@RequestBody UserRegistrationDTO user) {
        return userFacade.registerUser(user);
    }

}
