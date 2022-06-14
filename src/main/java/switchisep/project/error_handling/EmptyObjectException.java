package switchisep.project.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmptyObjectException extends RuntimeException{
    public EmptyObjectException(String message) {
        super(message);
    }

}
