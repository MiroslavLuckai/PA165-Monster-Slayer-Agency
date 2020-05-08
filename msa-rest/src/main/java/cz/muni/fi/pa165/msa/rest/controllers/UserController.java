package cz.muni.fi.pa165.msa.rest.controllers;


import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping
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
    public final UserDTO getUser(@PathVariable("id") long id) {

        logger.debug("Find user({})", id);
        return userFacade.findUserById(id);


    }
}
