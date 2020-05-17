package cz.muni.fi.pa165.msa.rest.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.msa.RootWebContext;
import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.facade.JobFacade;
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
public class JobControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private JobFacade facade;

    @Autowired
    @InjectMocks
    private JobController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllJobs() throws Exception {
        Mockito.doReturn(Collections.unmodifiableList(createJobs())).when(facade).findAll();

        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].severity").value("MODERATE"))
                .andExpect(jsonPath("$.[?(@.id==2)].severity").value("MINOR"));
    }

    @Test
    public void getJobById() throws Exception {
        Mockito.doReturn(Utils.getJobDTODummy1()).when(facade).findById(1L);

        mockMvc.perform(get("/jobs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].severity").value("MODERATE"));
    }

    @Test
    public void getJobWithWrongId() throws Exception {
        Mockito.doReturn(null).when(facade).findById(1L);

        mockMvc.perform(get("/jobs/1"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createJob() throws Exception {
        JobDTO job = Utils.getJobDTODummy1();

        Mockito.doReturn(job).when(facade).createJobFromRequest(any(JobCreateDTO.class),any(Long.class));

        String json = convertObjectToJsonBytes(Utils.getJobCreateDTODummy());

        mockMvc.perform(
                post("/jobs/create/request/1").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteJob() throws Exception {
        Mockito.doNothing().when(facade).removeJob(1L);

        mockMvc.perform(delete("/jobs/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingMonster() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(facade).removeJob(1L);

        mockMvc.perform(delete("/jobs/1"))
                .andExpect(status().isUnprocessableEntity());
    }


    private List<JobDTO> createJobs() {
        return Arrays.asList(Utils.getJobDTODummy1(), Utils.getJobDTODummy2());
    }

    private static String convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }
}
