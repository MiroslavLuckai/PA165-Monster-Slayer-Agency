package cz.muni.fi.pa165.msa.rest.controllers;


import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.facade.UserFacade;
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

        logger.debug("Get all users");
        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserById(@PathVariable("id") Long id) {

        logger.debug("Find user({})", id);
        return userFacade.findUserById(id);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByEmail(@PathVariable("email") String email) {

        logger.debug("Find user({})", email);
        return userFacade.findUserByEmail(email);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByUsername(@PathVariable("username") String username) {

        logger.debug("Find user({})", username);
        return userFacade.findUserByUsername(username);
    }
}
