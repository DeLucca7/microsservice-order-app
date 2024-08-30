package microsservice_order_app.order_service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequestDto {
    private Long idProduct;
    private Integer qty;
}
