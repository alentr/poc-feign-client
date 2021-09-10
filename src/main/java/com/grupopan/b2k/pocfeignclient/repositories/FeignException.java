package com.grupopan.b2k.pocfeignclient.repositories;

import org.springframework.http.HttpStatus;

public class FeignException extends RuntimeException {

  private HttpStatus httpCode;
  private String body;

  public FeignException(HttpStatus httpCode, String body, String message) {
    super(message);
    this.httpCode = httpCode;
    this.body = body;
  }

  public HttpStatus getHttpCode() {
    return httpCode;
  }

  public String getBody() {
    return body;
  }
}
