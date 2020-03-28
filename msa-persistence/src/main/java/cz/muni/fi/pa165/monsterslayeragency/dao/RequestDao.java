package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.util.List;

public interface RequestDao {

    /**
     * Adds request to database
     * @param request entity to be added to database
     */
    void addRequest(Request request);

    /**
     * Removes request from database
     * @param request entity to be removed from database
     */
    void removeRequest(Request request);

    /**
     * Updates request in database
     * @param request entity to be updated in database
     */
    void updateRequest(Request request);

    /**
     * Finds request in database by ID
     * @param id of the request
     * @return request with the id
     */
    Request findRequestById(Long id);

    /**
     * Finds request in database by customer who created it
     * @param user entity representing the customer
     * @return request requested by the user
     */
    Request findRequestByCustomer(User user);

    /**
     * Finds all requests in the database
     * @return list of all requests
     */
    List<Request> findAll();
}
