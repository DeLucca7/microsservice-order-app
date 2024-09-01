package microsservice_order_app.order_service.controllers;

import lombok.RequiredArgsConstructor;
import microsservice_order_app.order_service.dtos.requests.OrderRequestDto;
import microsservice_order_app.order_service.models.Order;
import microsservice_order_app.order_service.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.createOrder(requestDto));
    }
}
