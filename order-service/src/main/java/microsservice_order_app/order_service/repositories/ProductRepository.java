package microsservice_order_app.order_service.repositories;

import microsservice_order_app.order_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
