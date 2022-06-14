package switchisep.project.error_handling;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiErrorTest {

    @Test
    void test() {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "message");

        String error = apiError.getMessage();

        assertEquals("message", error);
    }
}