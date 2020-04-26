package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

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
    void addMonster(Request request, Monster monster);

    /**
     * Removes a monster from the request
     * @param request request
     * @param monster monster to be removed
     */
    void removeMonster(Request request, Monster monster);

    /**
     * Changes the location of the request
     * @param request request
     * @param location location to be changed
     */
    void changeLocation(Request request, String location);

    /**
     * Changes the reward from the request
     * @param request request
     * @param award number of money awarded for completion
     */
    void changeAward(Request request, BigDecimal award);

}
