package microsservice_order_app.order_service.repositories;

import microsservice_order_app.order_service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
