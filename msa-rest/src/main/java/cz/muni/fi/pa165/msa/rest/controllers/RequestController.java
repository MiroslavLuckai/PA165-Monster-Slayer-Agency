package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.RequestFacade;
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
public class RequestController {

    final static Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestFacade requestFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<RequestDTO> getRequests() {

        logger.debug("Get all requests");
        return requestFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RequestDTO getRequestById(@PathVariable("id") Long id) {

        logger.debug("Find request({})", id);
        return requestFacade.findById(id);
    }
}
