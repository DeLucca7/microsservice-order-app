package microsservice_order_app.auth_service.config;

import feign.codec.ErrorDecoder;
import microsservice_order_app.auth_service.dataproviders.client.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}