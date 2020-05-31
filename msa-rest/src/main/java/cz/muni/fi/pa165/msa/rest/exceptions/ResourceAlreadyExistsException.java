package cz.muni.fi.pa165.msa.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ludovit Kopcsanyi
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "A resource with these parameters already exists")
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
