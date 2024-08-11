package microsservice_order_app.order_service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import microsservice_order_app.order_service.models.Product;
import microsservice_order_app.order_service.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderServiceApplication {
	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		productRepository.saveAll(Arrays.asList(
				new Product("Gift Card Play Store BRL100", BigDecimal.valueOf(100.00)),
				new Product("Gift Card Play Store BRL200", BigDecimal.valueOf(200.00))
		));
	}
}
