package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.RootWebContext;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.facade.HeroFacade;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Miroslav Luckai
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class HeroControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private HeroFacade facade;

    @Autowired
    @InjectMocks
    private HeroController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllHeroes() throws Exception {
        Mockito.doReturn(Collections.unmodifiableList(createHeroes())).when(facade).findAllHeroes();

        mockMvc.perform(get("/heroes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Witcher"))
                .andExpect(jsonPath("$.[?(@.id==2)].name").value("Vesemir"));
    }

    @Test
    public void getHeroById() throws Exception {
        Mockito.doReturn(Utils.getHeroDTODummy1()).when(facade).findHero(1L);

        mockMvc.perform(get("/heroes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Witcher"));
    }

    @Test
    public void getHeroWithWrongId() throws Exception {
        Mockito.doReturn(null).when(facade).findHero(1L);

        mockMvc.perform(get("/heroes/1"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void getHeroByName() throws Exception {
        Mockito.doReturn(Utils.getHeroDTODummy1()).when(facade).findByHeroName("Witcher");

        mockMvc.perform(get("/heroes/name/Witcher"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Witcher"));

    }

    @Test
    public void getNonExistingHeroByName() throws Exception {
        Mockito.doReturn(null).when(facade).findByHeroName("Witcher");

        mockMvc.perform(get("/heroes/name/Witcher"))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void getHeroByUserId() throws Exception {
        Mockito.doReturn(Utils.getHeroDTODummy1()).when(facade).findByUserId(1L);

        mockMvc.perform(get("/heroes/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Witcher"));
    }

    @Test
    public void getNonExistingHeroById() throws Exception {
        Mockito.doReturn(null).when(facade).findByUserId(1L);

        mockMvc.perform(get("/heroes/user/1"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createHero() throws Exception {
        HeroDTO hero = Utils.getHeroDTODummy1();

        Mockito.doReturn(hero).when(facade).createHero(
                any(HeroDTO.class));

        String json = Utils.convertObjectToJsonBytes(hero);

        mockMvc.perform(
                post("/heroes/create").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteHero() throws Exception {
        Mockito.doNothing().when(facade).removeHero(1L);

        mockMvc.perform(delete("/heroes/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingHero() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(facade).removeHero(1L);

        mockMvc.perform(delete("/heroes/1"))
                .andExpect(status().isUnprocessableEntity());
    }

    private List<HeroDTO> createHeroes() {
        return Arrays.asList(Utils.getHeroDTODummy1(), Utils.getHeroDTODummy2());
    }
}
