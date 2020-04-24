package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;

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

}
