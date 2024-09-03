package microsservice_order_app.api_gateway.config.security;

import microsservice_order_app.api_gateway.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthTokenFilter implements GatewayFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final List<String> endpointList = List.of(
                "/v1/auth/register",
                "/v1/auth/login",
                "/eureka",
                "/v3/api-docs",
                "/users/v3/api-docs",
                "/auth/v3/api-docs",
                "/orders/v3/api-docs"
                );
        Predicate<ServerHttpRequest> isSecuredApi = r -> endpointList.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if(isSecuredApi.test(request)){
            if(!hasAuthHeader(request)) return handleError(exchange);

            String authToken = request.getHeaders().getOrEmpty("Authorization").get(0);
            if(authToken != null && authToken.startsWith("Bearer ")) authToken = authToken.substring(7);

            try {
                tokenService.checkToken(authToken);
            } catch (Exception ex){
                return handleError(exchange);
            }
        }
        return chain.filter(exchange);
    }

    private boolean hasAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> handleError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}