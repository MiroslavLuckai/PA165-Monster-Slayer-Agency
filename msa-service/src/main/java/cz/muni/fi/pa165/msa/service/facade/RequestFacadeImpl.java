package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.RequestFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RequestFacadeImpl implements RequestFacade {

    @Autowired
    private RequestService requestService;

    @Autowired
    private BeanMappingService beanMappingService;


    @Override
    public Long createRequest(RequestCreateDTO request) {
        Request mappedRequest = beanMappingService.mapTo(request, Request.class);
        return requestService.create(mappedRequest).getId();
    }

    @Override
    public boolean removeRequest(Long id) {
        return false;
    }

    @Override
    public RequestDTO findById(Long id) {
        return null;
    }

    @Override
    public List<RequestDTO> findAll() {
        return null;
    }

    @Override
    public List<RequestDTO> findByCustomer(Long id) {
        return null;
    }
}
