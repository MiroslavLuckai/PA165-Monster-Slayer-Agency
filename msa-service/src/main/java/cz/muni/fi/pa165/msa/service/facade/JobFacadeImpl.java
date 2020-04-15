package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.facade.JobFacade;

import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
public class JobFacadeImpl implements JobFacade {

    // TODO finish when bean mapping service is done

    @Override
    public Long createJob(JobCreateDTO job) {
        return null;
    }

    @Override
    public void updateJob(JobDTO job) {

    }

    @Override
    public void removeJob(Long id) {

    }

    @Override
    public JobDTO findById(Long id) {
        return null;
    }

    @Override
    public List<JobDTO> findAll() {
        return null;
    }
}
