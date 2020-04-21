package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.RequestDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestDao requestDao;

    @Override
    public void create(Request request) {
        requestDao.addRequest(request);
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
}
