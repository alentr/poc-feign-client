package com.grupopan.b2k.pocfeignclient.datasources.restclient.config;

import com.grupopan.b2k.pocfeignclient.repositories.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoderConfig implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {

    String responseObject = getResponseObject(response);
    return new FeignException(HttpStatus.valueOf(response.status()), responseObject, responseObject);
  }

  private String getResponseObject(Response response) {
    String result = null;
    try {
      if (response.body() != null) {
        Reader responseReader = response.body().asReader(Charset.defaultCharset());
        BufferedReader reader = new BufferedReader(responseReader);
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
          sb.append(str);
        }
        result = sb.toString();
      }
    } catch (IOException e) {
      throw new FeignException(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
    }
    return result;
  }
}
