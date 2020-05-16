package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.RequestFacade;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceAlreadyExistsException;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/requests")
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

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final RequestDTO createRequest(@RequestBody RequestCreateDTO requestCreateDTO) {
        logger.debug("rest createRequest()");

        try {
            Long id = requestFacade.createRequest(requestCreateDTO);
            return requestFacade.findById(id);
        } catch (Exception e) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeRequest(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.debug("rest removeRequest(requestId={})", id);

        try {
            requestFacade.removeRequest(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
