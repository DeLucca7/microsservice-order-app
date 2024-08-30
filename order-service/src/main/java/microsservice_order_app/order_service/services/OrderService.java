package microsservice_order_app.order_service.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import microsservice_order_app.order_service.dtos.requests.OrderItemRequestDto;
import microsservice_order_app.order_service.dtos.requests.OrderRequestDto;
import microsservice_order_app.order_service.models.Order;
import microsservice_order_app.order_service.models.OrderItem;
import microsservice_order_app.order_service.models.Product;
import microsservice_order_app.order_service.repositories.OrderRepository;
import microsservice_order_app.order_service.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public Order createOrder(OrderRequestDto requestDto) {
        this.userService.checkIfUserExists(requestDto.getUserId());

        Order order = new Order();

        List<OrderItem> items = requestDto.getItems()
                .stream()
                .map(this::startOrderItemByDto)
                .collect(Collectors.toList());

        order.setOrderItems(items);
        order.setTotal(calculateTotalPrice(items));
        order.setUserId(requestDto.getUserId());

        return this.orderRepository.save(order);
    }

    private OrderItem startOrderItemByDto(OrderItemRequestDto itemDto) {
        Product product = this.productRepository.findById(itemDto.getIdProduct())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQty(itemDto.getQty());

        return item;
    }

    private BigDecimal calculateTotalPrice(List<OrderItem> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem item : items) {
            BigDecimal itemTotal = item.getProduct().getPrice().multiply(new BigDecimal(item.getQty()));
            totalPrice = totalPrice.add(itemTotal);
        }

        return totalPrice;
    }
}
