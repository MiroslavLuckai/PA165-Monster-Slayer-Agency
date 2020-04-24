package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.util.List;

public interface RequestService {

    Request create(Request request);

    void delete(Request request);

    List<Request> findAll();

    Request findById(Long id);

    Request findByCustomer(User customer);
}
