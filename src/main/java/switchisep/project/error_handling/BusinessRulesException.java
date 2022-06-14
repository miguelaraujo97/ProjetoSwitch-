package switchisep.project.error_handling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BusinessRulesException extends RuntimeException{

    public BusinessRulesException(String message) {
        super(message);
    }

}
