package microsservice_order_app.user_service.config;

import feign.codec.ErrorDecoder;
import microsservice_order_app.user_service.client.CustomErrorDecode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecode();
    }
}