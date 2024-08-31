package microsservice_order_app.order_service.handlers;

import microsservice_order_app.order_service.dtos.responses.ErrorResponseDto;
import microsservice_order_app.order_service.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> userNotFoundException(UserNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
