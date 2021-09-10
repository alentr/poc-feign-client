package com.grupopan.b2k.pocfeignclient.datasources.restclient.config.poc;

import com.grupopan.b2k.pocfeignclient.datasources.restclient.config.GenericFeignClientRetryerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PocFeignClientRetryerConfig extends GenericFeignClientRetryerConfig {

  public PocFeignClientRetryerConfig(
      @Value("${feign.client.retry.poc.max.attempt}") int pocRetryMaxAttempt,
      @Value("${feign.client.retry.poc.interval}") long pocRetryInterval) {
    super(pocRetryMaxAttempt, pocRetryInterval);
  }
}
