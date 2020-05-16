package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Filip Daniel Fedin
 */
public interface RequestFacade {
    /**
     * Creates request.
     * @param request request to be created
     * @return id of the new request
     */
    Long createRequest(RequestCreateDTO request);


    /**
     * Removes request.
     * @param id of a request to be removed
     */
    void removeRequest(Long id);

    /**
     * Find request by id.
     * @param id id of a request to be found
     * @return request with selected id
     */
    RequestDTO findById(Long id);

    /**
     * Finds all requests.
     * @return list of all request.
     */
    List<RequestDTO> findAll();

    /**
     * Finds request by customer
     * @param customer customer
     * @return request with the selected customer
     */
    RequestDTO findByCustomer(UserDTO customer);

    /**
     * Adds a monster to the request
     * @param request request
     * @param monster monster to be added
     */
    void addMonster(RequestDTO request, MonsterDTO monster);

    /**
     * Removes a monster from the request
     * @param request request
     * @param monster monster to be removed
     */
    void removeMonster(RequestDTO request, MonsterDTO monster);

    /**
     * Changes the location of the request
     * @param request request
     * @param location location to be changed
     */
    void changeLocation(RequestDTO request, String location);

    /**
     * Changes the reward from the request
     * @param request request
     * @param award number of money awarded for completion
     */
    void changeAward(RequestDTO request, BigDecimal award);

    /**
     * Compares hero to all requests and returns compatible requests
     * @param hero - DTO of the hero to be compared
     * @return true if hero matches the requiered skillset for the request
     */
    List<RequestDTO> matchRequestsToHero(HeroDTO hero);
}
