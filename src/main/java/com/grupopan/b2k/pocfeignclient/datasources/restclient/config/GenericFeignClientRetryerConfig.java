package com.grupopan.b2k.pocfeignclient.datasources.restclient.config;

import com.grupopan.b2k.pocfeignclient.datasources.restclient.poc.FeignRetryException;
import feign.RetryableException;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericFeignClientRetryerConfig implements Retryer {

  Logger log = LoggerFactory.getLogger(GenericFeignClientRetryerConfig.class);

  protected int retryMaxAttempt;
  protected long retryInterval;
  private int attempt = 1;

  public GenericFeignClientRetryerConfig(int retryMaxAttempt, long retryInterval) {
    this.retryMaxAttempt = retryMaxAttempt;
    this.retryInterval = retryInterval;
  }

  @Override
  public void continueOrPropagate(RetryableException e) {
    log.info("Feign tentando reconectar pela {}° vez, por conta de {} ", attempt, e.getMessage());

    if(attempt++ == retryMaxAttempt){
      throw new FeignRetryException(this.retryMaxAttempt, this.retryInterval, e.getMessage());
    }
    try {
      Thread.sleep(retryInterval);
    } catch (InterruptedException ignored) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public Retryer clone() {
    return new GenericFeignClientRetryerConfig(retryMaxAttempt, retryInterval);
  }
}
