package cz.muni.fi.pa165.msa.service.utils;

/**
 * @author Michaela Bajanova (469166)
 */
public class Validator {

    public static void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
