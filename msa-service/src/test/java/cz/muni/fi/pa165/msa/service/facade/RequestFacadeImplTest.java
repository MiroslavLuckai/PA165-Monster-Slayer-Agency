package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.DummyObjects;
import cz.muni.fi.pa165.msa.service.RequestServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Filip Daniel Fedin
 */
public class RequestFacadeImplTest {

    @Autowired
    @InjectMocks
    private RequestFacadeImpl requestFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private RequestServiceImpl requestService;

    private Request request1;
    private Request request2;

    private User customer;

    private RequestDTO requestDTO1;
    private RequestDTO requestDTO2;

    private UserDTO userDTO;

    private RequestCreateDTO requestCreateDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void setUp() {

        requestDTO1 = DummyObjects.getRequestDTODummy1();
        requestDTO2 = DummyObjects.getRequestDTODummy2();

        userDTO = requestDTO1.getCustomer();

        requestCreateDTO = new RequestCreateDTO();
        requestCreateDTO.setCustomer(userDTO);
        requestCreateDTO.setAward(requestDTO1.getAward());
        requestCreateDTO.setMonsters(requestDTO1.getMonsters());
        requestCreateDTO.setLocation(requestDTO1.getLocation());



        request1 = DummyObjects.getRequestDummy1();
        customer = request1.getCustomer();
        request2 = DummyObjects.getRequestDummy2();
    }

    @AfterMethod
    void reset() {
        Mockito.reset(requestService);
    }

    @Test
    void create() {
        when(beanMappingService.mapTo(requestCreateDTO, Request.class)).thenReturn(request1);
        when(requestService.create(request1)).thenReturn(request1);
        Long createdId = requestFacade.createRequest(requestCreateDTO);
        verify(requestService).create(request1);
        Assert.assertEquals(createdId, request1.getId());
    }

    @Test
    void delete() {
        doNothing().when(requestService).delete(any());
        when(requestService.findById(request1.getId())).thenReturn(request1);
        requestFacade.removeRequest(request1.getId());
        verify(requestService).delete(request1);
    }

    @Test
    void findRequestById() {
        when(beanMappingService.mapTo(request1, RequestDTO.class)).thenReturn(requestDTO1);
        when(requestService.findById(request1.getId())).thenReturn(request1);
        RequestDTO tempRequestDTO = requestFacade.findById(request1.getId());
        Assert.assertEquals(tempRequestDTO, requestDTO1);
    }

    @Test
    void findRequestByCustomer() {
        when(beanMappingService.mapTo(request1, RequestDTO.class)).thenReturn(requestDTO1);
        when(requestService.findByCustomer(request1.getCustomer())).thenReturn(request1);
        when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(customer);
        RequestDTO tempRequestDTO = requestFacade.findByCustomer(requestDTO1.getCustomer());
        Assert.assertEquals(tempRequestDTO, requestDTO1);
    }

    @Test
    void findAllRequests() {
        List<Request> requestList = new ArrayList<>();
        requestList.add(request1);
        requestList.add(request2);
        when(requestService.findAll()).thenReturn(requestList);

        List<RequestDTO> requestDTOs = new ArrayList<>();
        requestDTOs.add(requestDTO1);
        requestDTOs.add(requestDTO2);
        when(beanMappingService.mapTo(requestList, RequestDTO.class)).thenReturn(requestDTOs);

        List<RequestDTO> actualRequestDTO = requestFacade.findAll();
        verify(requestService).findAll();
        Assert.assertEquals(actualRequestDTO, requestDTOs);
    }

    @Test
    void matchRequestsToHero() {
        List<Request> requestList = new ArrayList<>();
        requestList.add(request1);
        requestList.add(request2);
        HeroDTO heroDTO = DummyObjects.getHeroDTODummy1();
        Hero hero = DummyObjects.getHeroDummy1();

        List<RequestDTO> requestDTOList = new ArrayList<>();
        requestDTOList.add(requestDTO1);
        requestDTOList.add(requestDTO2);

        when(beanMappingService.mapTo(heroDTO, Hero.class)).thenReturn(hero);
        when(requestService.findAll()).thenReturn(requestList);
        when(beanMappingService.mapTo(requestList, RequestDTO.class)).thenReturn(requestDTOList);
        List<RequestDTO> matched = requestFacade.matchRequestsToHero(heroDTO);
        Assert.assertEquals(requestDTOList, matched);
    }

}
