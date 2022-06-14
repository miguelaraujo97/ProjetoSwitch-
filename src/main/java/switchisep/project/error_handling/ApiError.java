package switchisep.project.error_handling;


import lombok.Getter;
import org.springframework.http.HttpStatus;


public class ApiError {


    private  HttpStatus status;
    @Getter
    private final String message;

    public ApiError(HttpStatus statusEntry, String messageEntry) {
        super();
        this.status = statusEntry;
        this.message = messageEntry;

    }

}
