package by.sample.shopflow.order.config;

import feign.Target;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class FeignCircuitBreakerConfig {

    @Bean
    public CircuitBreakerNameResolver circuitBreakerNameResolver() {
        // Заставляем использовать имя Feign-клиента (name из @FeignClient)
        // вместо склеенной строки Класс+Метод+Параметры
        return (String feignClientName, Target<?> target, Method method) -> feignClientName;
    }
}
