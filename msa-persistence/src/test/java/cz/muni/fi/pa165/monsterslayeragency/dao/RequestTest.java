package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RequestTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RequestDao requestDao;

    private Request ghoulRequest;
    private Request golemRequest;
    private Monster ghoul;
    private Monster golem;

    @BeforeMethod
    public void setUp() {
        createGhoul();
        createGolem();
        createGhoulRequest();
        createGolemRequest();
        em.persist(ghoul);
        em.persist(golem);
        em.persist(ghoulRequest);
        em.persist(golemRequest);
    }

    @Test
    public void createRequestTest() {
        Monster basilisk = createBasilisk();
        em.persist(basilisk);
        Request basiliskRequest = createBasiliskRequest();
        basiliskRequest.getMonsters().add(basilisk);
        requestDao.addRequest(basiliskRequest);

        Assert.assertEquals(em.find(Request.class, basiliskRequest.getId()), basiliskRequest);
    }

    @Test
    public void getGhoulRequestById() {
        Request request = requestDao.findRequestById(ghoulRequest.getId());
        Assert.assertEquals(ghoulRequest, request);
    }

    @Test
    public void getAllRequests() {
        List<Request> requests = requestDao.findAll();
        Assert.assertEquals(requests.size(), 2);
    }

    @Test
    public void updateTest() {
        ghoulRequest.setLocation("Skellige");
        requestDao.updateRequest(ghoulRequest);
        Request request = em.find(Request.class, ghoulRequest.getId());
        Assert.assertEquals(ghoulRequest, request);
    }

    @Test
    public void deleteTest() {
        Request request = createBasiliskRequest();
        em.persist(request);
        List<Request> requests = em.createQuery("SELECT r FROM Request r", Request.class).getResultList();
        Assert.assertEquals(requests.size(), 3);

        requestDao.removeRequest(ghoulRequest);
        request = em.find(Request.class, ghoulRequest.getId());
        Assert.assertNull(request);
        requests = em.createQuery("SELECT r FROM Request r", Request.class).getResultList();
        Assert.assertEquals(requests.size(), 2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeWithNullRequest() {
        requestDao.removeRequest(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addWithNullRequest() {
        requestDao.addRequest(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNullRequest() {
        requestDao.updateRequest(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findWithNullId() {
        requestDao.findRequestById(null);
    }

    private void createGhoul() {
        ghoul = new Monster();
        ghoul.setName("Ghoul");
        ghoul.setSize(10);
        ghoul.setMonsterType(MonsterType.NECROPHAGE);
    }

    private void createGolem() {
        golem = new Monster();
        golem.setName("Golem");
        golem.setSize(25);
        golem.setMonsterType(MonsterType.ELEMENTA);
    }

    private Request createBasiliskRequest() {
        Request request = new Request();
        request.setLocation("Velen");
        request.setAward(new BigDecimal(1000));
        request.setMonsters(new ArrayList<>());
        return request;
    }

    private void createGhoulRequest() {
        ghoulRequest = new Request();
        ghoulRequest.setLocation("Velen");
        ghoulRequest.setAward(new BigDecimal(100));
        ghoulRequest.setMonsters(new ArrayList<>());
        ghoulRequest.getMonsters().add(ghoul);
    }

    private void createGolemRequest() {
        golemRequest = new Request();
        golemRequest.setLocation("Novigrad");
        golemRequest.setAward(new BigDecimal(10000));
        golemRequest.setMonsters(new ArrayList<>());
        golemRequest.getMonsters().add(golem);
    }

    private Monster createBasilisk() {
        Monster basilisk = new Monster();
        basilisk.setName("Basilisk");
        basilisk.setSize(40);
        basilisk.setMonsterType(MonsterType.DRACONID);
        return basilisk;
    }
}
