package microsservice_order_app.order_service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {
    private Long userId;
    private List<OrderItemRequestDto> items;
}
