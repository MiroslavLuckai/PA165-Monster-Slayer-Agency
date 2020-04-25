package cz.muni.fi.pa165.msa.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Michaela Bajanova (469166)
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
