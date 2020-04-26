package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface RequestService {

    Request create(Request request) throws IllegalArgumentException;

    void delete(Request request) throws IllegalArgumentException;

    List<Request> findAll();

    Request findByCustomer(User customer);

    void addMonster(Request request, Monster monster);

    void removeMonster(Request request, Monster monster);

    Request findById(Long id) throws IllegalArgumentException;

    void changeLocation(Request request, String location);

    void changeAward(Request request, BigDecimal award);

    void removeMonsters(Request request, List<Monster> monsters) throws IllegalArgumentException;

    void changeLocation(Request request, String location) throws IllegalArgumentException;

    void changeAward(Request request, BigDecimal award) throws IllegalArgumentException;
}
