package cz.muni.fi.pa165.msa.sampledata;

import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoaderImpl.class})
public class MSAWithSampleDataConfiguration {

    final static Logger log = LoggerFactory.getLogger(MSAWithSampleDataConfiguration.class);

    @Autowired
    SampleDataLoader sampleDataLoader;

    @PostConstruct
    public void dataLoading() throws IOException {
        log.info("Populating database");
        sampleDataLoader.populates();
    }
}
