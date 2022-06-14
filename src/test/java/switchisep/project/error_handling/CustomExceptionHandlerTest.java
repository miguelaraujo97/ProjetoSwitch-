package switchisep.project.error_handling;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionHandlerTest {

    @Test
    void handlerBusinessException() {

        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

        BusinessRulesException e = new BusinessRulesException("edeerfre");

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(badRequest, e.getMessage());

        ResponseEntity<Object> response = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);

        ResponseEntity<Object> actual =  customExceptionHandler.handlerIllegalArgumentException(e);
        assertNotEquals(response, actual);
    }
}