package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.HeroFacade;
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
import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {

    final static Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestFacade requestFacade;

    @Autowired
    private HeroFacade heroFacade;

    /**
     * Get all requests stored in database
     * curl -i -X GET http://localhost:8080/pa165/rest/requests
     * @return List of RequestDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<RequestDTO> getRequests() {

        logger.debug("Get all requests");
        return requestFacade.findAll();
    }

    /**
     * Get Request by its id
     * curl -i -X GET http://localhost:8080/pa165/rest/requests/{id}
     * @param id id of the request
     * @return request DTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RequestDTO getRequestById(@PathVariable("id") Long id) {

        logger.debug("Find request({})", id);
        RequestDTO request = requestFacade.findById(id);
        if (request == null) {
            throw new ResourceNotFoundException();
        }
        return request;
    }

    /**
     * Get list of request doable for a hero
     * curl -i -X GET http://localhost:8080/pa165/rest/requests/match/{id}
     * @param id id of the hero we are looking requests for
     * @return List of Requests
     */
    @RequestMapping(value = "/match/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<RequestDTO> matchRequestsToHero(@PathVariable("id") Long id) {
        logger.debug("Find Hero({})", id);
        HeroDTO heroDTO = heroFacade.findHero(id);
        logger.debug("Matching");
        return requestFacade.matchRequestsToHero(heroDTO);
    }

    /**
     * Create new request
     * curl -X POST -i -H "Content-Type: application/json" --data '{data}' http://localhost:8080/pa165/rest/requests/create
     * @param requestCreateDTO RequestDto we want to create
     * @return newly created request
     */
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

    /**
     * Delete request from database
     * curl -i -X DELETE http://localhost:8080/pa165/rest/requests/{id}
     * @param id id of the request we want to delete
     * @throws ResourceNotFoundException when request with id was not found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeRequest(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.debug("rest removeRequest(requestId={})", id);

        try {
            requestFacade.removeRequest(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
