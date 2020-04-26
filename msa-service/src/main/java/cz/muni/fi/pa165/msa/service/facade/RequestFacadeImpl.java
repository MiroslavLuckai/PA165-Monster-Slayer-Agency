package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;
import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.facade.RequestFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class RequestFacadeImpl implements RequestFacade {

    @Autowired
    private RequestService requestService;

    @Autowired
    private BeanMappingService beanMappingService;


    @Override
    public Long createRequest(RequestCreateDTO request) {
        Request mappedRequest = beanMappingService.mapTo(request, Request.class);
        Request created = requestService.create(mappedRequest);
        return created.getId();
    }

    @Override
    public void removeRequest(Long id) {
        Request toBeDeleted = requestService.findById(id);
        requestService.delete(toBeDeleted);
    }

    @Override
    public RequestDTO findById(Long id) {
        Request found = requestService.findById(id);
        return beanMappingService.mapTo(found, RequestDTO.class);
    }

    @Override
    public List<RequestDTO> findAll() {
        List<Request> found = requestService.findAll();
        return beanMappingService.mapTo(found, RequestDTO.class);
    }

    @Override
    public RequestDTO findByCustomer(UserDTO customer) {
        User user = beanMappingService.mapTo(customer, User.class);
        Request found = requestService.findByCustomer(user);
        return beanMappingService.mapTo(found, RequestDTO.class);
    }

    @Override
    public void addMonster(RequestDTO request, MonsterDTO monster) {
        Request mappedRequest = beanMappingService.mapTo(request, Request.class);
        Monster mappedMonster = beanMappingService.mapTo(monster, Monster.class);
        requestService.addMonster(mappedRequest, mappedMonster);
    }

    @Override
    public void removeMonster(RequestDTO request, MonsterDTO monster) {
        Request mappedRequest = beanMappingService.mapTo(request, Request.class);
        Monster mappedMonster = beanMappingService.mapTo(monster, Monster.class);
        requestService.removeMonster(mappedRequest, mappedMonster);
    }

    @Override
    public void changeLocation(RequestDTO request, String location) {
        Request mappedRequest = beanMappingService.mapTo(request, Request.class);
        requestService.changeLocation(mappedRequest, location);
    }

    @Override
    public void changeAward(RequestDTO request, BigDecimal award) {
        Request mappedRequest = beanMappingService.mapTo(request, Request.class);
        requestService.changeAward(mappedRequest, award);
    }

}
