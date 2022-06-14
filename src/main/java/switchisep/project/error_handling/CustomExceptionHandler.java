package switchisep.project.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method to threat the Illegal argument and null pinter exception.
     *
     * @return responseEntity with time stamp and message with error
     * description.
     */
    @ExceptionHandler({
            IllegalArgumentException.class,
            NullPointerException.class,
            BusinessRulesException.class,
            EmptyObjectException.class})
    public ResponseEntity<Object> handlerIllegalArgumentException(RuntimeException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiError apiError = new ApiError(badRequest, e.getMessage());

        return new ResponseEntity<>(apiError, badRequest);
    }


}
