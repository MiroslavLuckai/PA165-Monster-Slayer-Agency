package cz.muni.fi.pa165.msa.sampledata;

import java.io.IOException;

/**
 * Implementation of this class populates inmemory database
 * @author Miroslav Luckai
 */
public interface SampleDataLoader {
    void populates() throws IOException;
}
