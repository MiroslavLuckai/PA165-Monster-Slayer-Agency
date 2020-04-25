package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.RequestDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestDao requestDao;

    @Autowired
    MonsterDao monsterDao;

    @Override
    public Request create(Request request) {
        requestDao.addRequest(request);
        return request;
    }

    @Override
    public void delete(Request request) {
        requestDao.removeRequest(request);
    }

    @Override
    public List<Request> findAll() {
        return requestDao.findAll();
    }

    @Override
    public Request findById(Long id) {
        return requestDao.findRequestById(id);
    }

    @Override
    public Request findByCustomer(User customer) {
        return requestDao.findRequestByCustomer(customer);
    }

    @Override
    public void addMonsters(Request request, List<Monster> monsters) {
        Request found = requestDao.findRequestById(request.getId());
        found.getMonsters().addAll(monsters);
        requestDao.updateRequest(found);
    }

    @Override
    public void removeMonsters(Request request, List<Monster> monsters) {
        validateAddedMonsters(monsters);
        Request found = requestDao.findRequestById(request.getId());
        found.getMonsters().removeAll(monsters);
        requestDao.updateRequest(found);
    }

    @Override
    public void changeLocation(Request request, String location) {
        Request found = requestDao.findRequestById(request.getId());
        found.setLocation(location);
        requestDao.updateRequest(found);
    }

    @Override
    public void changeAward(Request request, BigDecimal award) {
        Request found = requestDao.findRequestById(request.getId());
        found.setAward(award);
        requestDao.updateRequest(found);
    }

    private void validateAddedMonsters(List<Monster> monsters) {
        for (Monster monster: monsters) {
            Monster found = monsterDao.findById(monster.getId());
            if (found == null) {
                monsterDao.addMonster(monster);
            }
        }
    }
}
