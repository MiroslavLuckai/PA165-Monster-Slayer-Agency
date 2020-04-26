package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Filip Daniel Fedin
 */
public interface RequestService {

    Request create(Request request) throws IllegalArgumentException;

    void delete(Request request) throws IllegalArgumentException;

    List<Request> findAll();

    Request findByCustomer(User customer) throws IllegalArgumentException;

    void addMonster(Request request, Monster monster) throws IllegalArgumentException;

    void removeMonster(Request request, Monster monster) throws IllegalArgumentException;

    Request findById(Long id) throws IllegalArgumentException;

    void changeAward(Request request, BigDecimal award) throws IllegalArgumentException;

    void changeLocation(Request request, String location) throws IllegalArgumentException;
}
