package cz.muni.fi.pa165.msa.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String,String> getResources() {
        Map<String, String> resourceMap = new HashMap<>();
        resourceMap.put("Users", "/users");
        resourceMap.put("Requests", "/requests");
        resourceMap.put("Monsters", "/monsters");
        resourceMap.put("Heroes", "/heroes");
        resourceMap.put("Jobs", "/jobs");
        return Collections.unmodifiableMap(resourceMap);
    }
}
