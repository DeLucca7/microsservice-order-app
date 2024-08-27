package microsservice_order_app.order_service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponseDto {
    private String message;
    private HttpStatus status;
    private LocalDateTime time;
}
