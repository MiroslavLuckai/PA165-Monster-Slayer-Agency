package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.RequestDao;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class RequestServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RequestDao requestDao;

    @Autowired
    @InjectMocks
    private RequestService requestService;

    @Test
    public void create() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByCustomer() {
    }
}