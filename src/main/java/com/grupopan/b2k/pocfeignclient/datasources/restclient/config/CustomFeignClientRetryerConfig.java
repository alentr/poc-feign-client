package com.grupopan.b2k.pocfeignclient.datasources.restclient.config;

import feign.RetryableException;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomFeignClientRetryerConfig implements Retryer {

  Logger log = LoggerFactory.getLogger(CustomFeignClientRetryerConfig.class);

  @Value("${feign.client.retry.max.attempt}")
  private int retryMaxAttempt;

  @Value("${feign.client.retry.interval}")
  private long retryInterval;

  private int attempt = 1;

  public CustomFeignClientRetryerConfig() {
  }

  public CustomFeignClientRetryerConfig(int retryMaxAttempt, Long retryInterval) {
    this.retryMaxAttempt = retryMaxAttempt;
    this.retryInterval = retryInterval;
  }

  @Override
  public void continueOrPropagate(RetryableException e) {
    log.info("Feign tentando reconectar pela {}° vez, por conta de {} ", attempt, e.getMessage());

    if(attempt++ == retryMaxAttempt){
      throw e;
    }
    try {
      Thread.sleep(retryInterval);
    } catch (InterruptedException ignored) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public Retryer clone() {
    return new CustomFeignClientRetryerConfig(retryMaxAttempt, retryInterval);
  }
}
