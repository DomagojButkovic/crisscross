package app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dm on 11.02.17..
 */
 @ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Error")
 public class GameErrorException extends Exception {

        public GameErrorException() {
        }
    }
