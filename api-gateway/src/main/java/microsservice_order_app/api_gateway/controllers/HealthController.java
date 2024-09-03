package microsservice_order_app.api_gateway.controllers;

import microsservice_order_app.api_gateway.services.ServicesHealthIndicator;
import microsservice_order_app.api_gateway.services.dtos.HealthStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final ServicesHealthIndicator healthIndicator;

    public HealthController(ServicesHealthIndicator eurekaHealthIndicator) {
        this.healthIndicator = eurekaHealthIndicator;
    }

    @GetMapping("/health-check")
    public List<HealthStatusDto> health() {
        return (List<HealthStatusDto>) healthIndicator.health().getDetails().get("Service Status");
    }
}