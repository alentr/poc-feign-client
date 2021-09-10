package com.grupopan.b2k.pocfeignclient.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupopan.b2k.pocfeignclient.repositories.FeignException;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdviceConfig {

  @ResponseBody
  @ExceptionHandler(FeignException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GeneralError feignExceptionHandler(FeignException e) {
    GeneralError generalError = new GeneralError();
    generalError.setCode(500);
    generalError.setMessage(e.getMessage());

    return generalError;
  }

  @ResponseBody
  @ExceptionHandler(RetryableException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GeneralError RetryableExceptionHandler(RetryableException e) {
    GeneralError generalError = new GeneralError();
    generalError.setCode(500);
    generalError.setMessage(e.getMessage());

    return generalError;
  }
}

//TODO: essa classe é da lib do PAN
class GeneralError {
  @JsonProperty("code")
  private Integer code;

  @JsonProperty("message")
  private String message;

  public GeneralError code(Integer code) {
    this.code = code;
    return this;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public GeneralError message(String message) {
    this.message = message;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
