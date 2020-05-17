package cz.muni.fi.pa165.msa.rest.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.msa.RootWebContext;
import cz.muni.fi.pa165.msa.dto.RequestCreateDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.RequestFacade;
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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class RequestControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RequestFacade facade;

    @Autowired
    @InjectMocks
    private RequestController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllRequests() throws Exception {
        Mockito.doReturn(Collections.unmodifiableList(createRequests())).when(facade).findAll();

        mockMvc.perform(get("/requests"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].location").value("Novigrad"))
                .andExpect(jsonPath("$.[?(@.id==2)].location").value("Velen"));
    }

    @Test
    public void getRequestById() throws Exception {
        Mockito.doReturn(DummyObjects.getRequestDTODummy1()).when(facade).findById(1L);

        mockMvc.perform(get("/requests/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].location").value("Novigrad"));
    }

    @Test
    public void getRequestWithWrongId() throws Exception {
        Mockito.doReturn(null).when(facade).findById(1L);

        mockMvc.perform(get("/requests/1")).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRequest() throws Exception {
        RequestDTO request = DummyObjects.getRequestDTODummy1();

        Mockito.doReturn(1L).when(facade).createRequest(
                any(RequestCreateDTO.class));

        String json = this.convertObjectToJsonBytes(request);

        mockMvc.perform(
                post("/requests/create").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRequest() throws Exception {
        Mockito.doNothing().when(facade).removeRequest(1L);

        mockMvc.perform(delete("/requests/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingRequest() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(facade).removeRequest(1L);

        mockMvc.perform(delete("/requests/1"))
                .andExpect(status().isUnprocessableEntity());
    }


    private List<RequestDTO> createRequests() {
        return Arrays.asList(DummyObjects.getRequestDTODummy1(), DummyObjects.getRequestDTODummy2());
    }

    private static String convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

}
