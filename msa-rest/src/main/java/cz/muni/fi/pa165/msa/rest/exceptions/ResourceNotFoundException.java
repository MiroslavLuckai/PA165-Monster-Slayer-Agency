package cz.muni.fi.pa165.msa.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ludovit Kopcsanyi
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The resource was not found")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
