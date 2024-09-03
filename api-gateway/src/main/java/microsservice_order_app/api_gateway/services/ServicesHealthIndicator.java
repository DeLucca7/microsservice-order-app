package microsservice_order_app.api_gateway.services;

import microsservice_order_app.api_gateway.services.dtos.HealthStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesHealthIndicator implements HealthIndicator {

    @Autowired
    private DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Health health() {
        List<String> services = discoveryClient.getServices();
        List<HealthStatusDto> serviceStatusList = new ArrayList<>();

        for (String serviceId : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance instance : instances) {
                String url = instance.getUri().toString();
                try {
                    String response = restTemplate.getForObject(url + "/actuator/health", String.class);
                    if (response != null && response.contains("UP")) {
                        serviceStatusList.add(new HealthStatusDto(serviceId, "Healthy", null));
                    } else {
                        serviceStatusList.add(new HealthStatusDto(serviceId, "Unhealthy", "Service response isn't 'UP'"));
                    }
                } catch (Exception e) {
                    serviceStatusList.add(new HealthStatusDto(serviceId, "Unhealthy", e.getMessage()));
                }
            }
        }

        boolean allHealthy = serviceStatusList.stream().allMatch(status -> "Healthy".equals(status.getStatus()));
        Health.Builder healthBuilder = allHealthy ? Health.up() : Health.down();
        return healthBuilder.withDetail("Service Status", serviceStatusList).build();
    }
}
