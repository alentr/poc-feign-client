package com.grupopan.b2k.pocfeignclient.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupopan.b2k.pocfeignclient.datasources.restclient.poc.FeignRetryException;
import com.grupopan.b2k.pocfeignclient.repositories.FeignException;
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
  @ExceptionHandler(FeignRetryException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GeneralError FeignRetryExceptionHandler(FeignRetryException e) {
    GeneralError generalError = new GeneralError();
    generalError.setCode(500);
    generalError.setMessage(String.format(
        "Erro de conexão no Feign. Foi tentado reconectar %d vezes com um intervalalo de %d milisegundos. Mensagem de erro: %s",
        e.getRetryMaxAttempt(), e.getRetryInterval(), e.getMessage()));

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
