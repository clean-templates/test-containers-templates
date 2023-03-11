package com.order.service.configs;

import com.order.service.infra.external.services.DeliveryFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = {DeliveryFeignClient.class})
public class FeignClientConfig {
}
