package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

public class FeignRetryException extends RuntimeException {

  private int retryMaxAttempt;
  private long retryInterval;

  public FeignRetryException(int retryMaxAttempt, long retryInterval, String message) {
    super(message);
    this.retryMaxAttempt = retryMaxAttempt;
    this.retryInterval = retryInterval;
  }

  public int getRetryMaxAttempt() {
    return retryMaxAttempt;
  }

  public long getRetryInterval() {
    return retryInterval;
  }
}
