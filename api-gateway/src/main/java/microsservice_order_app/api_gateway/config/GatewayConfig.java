package microsservice_order_app.api_gateway.config;

import microsservice_order_app.api_gateway.config.security.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", route -> route.path("/v1/user/**")
                        .filters(f -> f.filter(authTokenFilter))
                        .uri("lb://user-service"))
                .route("user-service-swagger", route -> route.path("/user-service/v3/api-docs/**")
                        .uri("lb://user-service"))
                .route("order-service", route -> route.path("/v1/order/**")
                        .filters(f -> f.filter(authTokenFilter))
                        .uri("lb://order-service"))
                .route("order-service-swagger", route -> route.path("/order-service/v3/api-docs/**")
                        .uri("lb://order-service"))
                .route("auth-service", route -> route.path("/v1/auth/**")
                        .uri("lb://auth-service"))
                .route("auth-service-swagger", route -> route.path("/auth-service/v3/api-docs/**")
                        .uri("lb://auth-service"))
                .build();
    }
}