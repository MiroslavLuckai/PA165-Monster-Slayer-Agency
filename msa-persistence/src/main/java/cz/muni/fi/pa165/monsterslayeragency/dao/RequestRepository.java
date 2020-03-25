package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Filip Daniel Fedin
 */

public interface RequestRepository extends CrudRepository<Request, Long> {

    /**
     * Finds request by the customer who ordered it.
     * @param customer User object representing the customer
     * @return Request requested by the customer
     */
    Request findByCustomer(User customer);

}
