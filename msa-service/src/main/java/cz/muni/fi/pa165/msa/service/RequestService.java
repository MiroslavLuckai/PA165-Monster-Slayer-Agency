package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface RequestService {

    Request create(Request request);

    void delete(Request request);

    List<Request> findAll();

    Request findById(Long id);

    Request findByCustomer(User customer);

    void addMonsters(Request request, List<Monster> monsters);

    void removeMonsters(Request request, List<Monster> monsters);

    void changeLocation(Request request, String location);

    void changeAward(Request request, BigDecimal award);




}
