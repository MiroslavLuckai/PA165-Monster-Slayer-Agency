package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.RootWebContext;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.dto.UserRegistrationDTO;
import cz.muni.fi.pa165.msa.facade.UserFacade;
import cz.muni.fi.pa165.msa.rest.Utils;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Miroslav Luckai
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserFacade facade;

    @Autowired
    @InjectMocks
    private UserController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(facade);
    }

    @Test
    public void getAllUsers() throws Exception {
        Mockito.doReturn(Collections.unmodifiableList(createUsers())).when(facade).getAllUsers();

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].userName").value("Geralt"))
                .andExpect(jsonPath("$.[?(@.id==2)].userName").value("Peter"));
    }

    @Test
    public void getUserById() throws Exception {
        Mockito.doReturn(Utils.getUserDTODummy1()).when(facade).findUserById(1L);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].userName").value("Geralt"));
    }

    @Test
    public void getUserWithWrongId() throws Exception {
        Mockito.doReturn(null).when(facade).findUserById(1L);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createUser() throws Exception {
        UserDTO user = Utils.getUserDTODummy1();
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
        registrationDTO.setPassword("Password1");
        registrationDTO.setUser(user);

        Mockito.doReturn(user).when(facade).registerUser(
                any(UserRegistrationDTO.class));

        String json = Utils.convertObjectToJsonBytes(registrationDTO);

        mockMvc.perform(
                post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {
        Mockito.doNothing().when(facade).removeUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingUser() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(facade).removeUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isUnprocessableEntity());
    }

    private List<UserDTO> createUsers() {
        return Arrays.asList(Utils.getUserDTODummy1(), Utils.getUserDTODummy2());
    }
}
