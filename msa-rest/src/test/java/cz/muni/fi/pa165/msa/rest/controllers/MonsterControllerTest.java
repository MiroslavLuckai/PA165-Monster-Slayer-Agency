package cz.muni.fi.pa165.msa.rest.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.msa.RootWebContext;
import cz.muni.fi.pa165.msa.dto.MonsterCreateDTO;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;
import cz.muni.fi.pa165.msa.facade.MonsterFacade;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class MonsterControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MonsterFacade facade;

    @Autowired
    @InjectMocks
    private MonsterController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllMonsters() throws Exception {
        Mockito.doReturn(Collections.unmodifiableList(createMonsters())).when(facade).findAll();

        mockMvc.perform(get("/monsters"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Beast Monster"))
                .andExpect(jsonPath("$.[?(@.id==2)].name").value("Hybrid Monster"));
    }

    @Test
    public void getMonsterById() throws Exception {
        Mockito.doReturn(Utils.getMonsterDTODummy1()).when(facade).findById(1L);

        mockMvc.perform(get("/monsters/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Beast Monster"));
    }

    @Test
    public void getMonsterWithWrongId() throws Exception {
        Mockito.doReturn(null).when(facade).findById(1L);

        mockMvc.perform(get("/monsters/1")).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createMonster() throws Exception {
        MonsterDTO monster = Utils.getMonsterDTODummy1();

        Mockito.doReturn(1L).when(facade).createMonster(
                any(MonsterCreateDTO.class));

        String json = convertObjectToJsonBytes(monster);

        mockMvc.perform(
                post("/monsters/create").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMonster() throws Exception {
        Mockito.doNothing().when(facade).removeMonster(1L);

        mockMvc.perform(delete("/monsters/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingMonster() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(facade).removeMonster(1L);

        mockMvc.perform(delete("/monsters/1"))
                .andExpect(status().isUnprocessableEntity());
    }


    private List<MonsterDTO> createMonsters() {
        return Arrays.asList(Utils.getMonsterDTODummy1(), Utils.getMonsterDTODummy2());
    }

    private static String convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

}