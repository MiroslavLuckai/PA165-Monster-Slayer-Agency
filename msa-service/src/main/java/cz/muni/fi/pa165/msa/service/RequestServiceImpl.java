package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.RequestDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Filip Daniel Fedin
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestDao requestDao;

    @Autowired
    MonsterDao monsterDao;

    private String REQUEST_IS_NULL_MESSAGE = "Request cannot be null.";
    private String MONSTER_IS_NULL_MESSAGE = "Monster cannot be null.";

    @Override
    public Request create(Request request) {
        Validator.validate(request, REQUEST_IS_NULL_MESSAGE);
        requestDao.addRequest(request);
        return request;
    }

    @Override
    public void delete(Request request) {
        Validator.validate(request, REQUEST_IS_NULL_MESSAGE);
        requestDao.removeRequest(request);
    }

    @Override
    public List<Request> findAll() {
        return requestDao.findAll();
    }

    @Override
    public Request findById(Long id) {
        Validator.validate(id, "Id cannot be null.");
        return requestDao.findRequestById(id);
    }

    @Override
    public Request findByCustomer(User customer) {
        Validator.validate(customer, "Customer cannot be null.");
        return requestDao.findRequestByCustomer(customer);
    }

    @Override
    public void addMonster(Request request, Monster monster) {
        Validator.validate(request, REQUEST_IS_NULL_MESSAGE);
        Validator.validate(monster, MONSTER_IS_NULL_MESSAGE);
        Request found = requestDao.findRequestById(request.getId());
        Monster exists = monsterDao.findById(monster.getId());
        if (found != null && exists != null) {
            found.getMonsters().add(monster);
            requestDao.updateRequest(found);
        }
    }

    @Override
    public void removeMonster(Request request, Monster monster) {
        Validator.validate(request, REQUEST_IS_NULL_MESSAGE);
        Validator.validate(monster, MONSTER_IS_NULL_MESSAGE);
        Monster exists = monsterDao.findById(monster.getId());
        Request found = requestDao.findRequestById(request.getId());
        if (found != null && exists != null) {
            found.getMonsters().remove(monster);
            requestDao.updateRequest(found);
        }
    }

    @Override
    public void changeLocation(Request request, String location) {
        Validator.validate(request, REQUEST_IS_NULL_MESSAGE);
        Validator.validate(location, "Location cannot be null.");
        Request found = requestDao.findRequestById(request.getId());
        if (found != null) {
            found.setLocation(location);
            requestDao.updateRequest(found);
        }
    }

    @Override
    public void changeAward(Request request, BigDecimal award) {
        Validator.validate(request, REQUEST_IS_NULL_MESSAGE);
        Validator.validate(award, "Award cannot be null.");
        Request found = requestDao.findRequestById(request.getId());
        if (found != null) {
            found.setAward(award);
            requestDao.updateRequest(found);
        }
    }
}
