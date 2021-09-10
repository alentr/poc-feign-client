package com.grupopan.b2k.pocfeignclient.datasources.restclient.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.grupopan.b2k.pocfeignclient.datasources.restclient"})
public class FeignConfig {

}
