package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.UserDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.RequestFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.DummyObjects;
import cz.muni.fi.pa165.msa.service.RequestService;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class RequestFacadeImplTest extends AbstractTestNGSpringContextTests {


    @Mock
    private RequestService requestService;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MonsterDao monsterDao;


    @Autowired
    @InjectMocks
    private RequestFacade requestFacade;

    private RequestDTO requestDTO;
    private RequestCreateDTO requestCreateDTO;
    private Request request;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {

       request = DummyObjects.getRequestDummy1();
       userDao.addUser(request.getCustomer());
        for (Monster m: request.getMonsters()) {
            monsterDao.addMonster(m);
        }

       requestDTO = DummyObjects.getRequestDTODummy1();

       requestCreateDTO = new RequestCreateDTO();
       requestCreateDTO.setAward(requestDTO.getAward());
       requestCreateDTO.setCustomer(requestDTO.getCustomer());
       requestCreateDTO.setLocation(requestDTO.getLocation());
       requestCreateDTO.setMonsters(requestDTO.getMonsters());


        when(beanMappingService.mapTo(requestDTO, Request.class)).thenReturn(request);
        when(beanMappingService.mapTo(request, RequestDTO.class)).thenReturn(requestDTO);

        when(requestService.findById(request.getId())).thenReturn(request);
        when(requestService.findByCustomer(request.getCustomer())).thenReturn(request);
    }


    @Test
    public void testCreateRequest() {
//        Assert.assertEquals(request.getId(), requestFacade.createRequest(requestCreateDTO));
//        verify(requestService, times(1)).create(request);
    }

    @Test
    public void testRemoveRequest() {
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testFindByCustomer() {
    }
}