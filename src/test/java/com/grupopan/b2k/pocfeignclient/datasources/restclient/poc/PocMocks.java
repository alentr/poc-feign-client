package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class PocMocks {

  private static ObjectMapper mapper = new ObjectMapper();

  public static void setupGetPocResponse(WireMockServer mockService) throws IOException {
    mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/meu-servico/poc/get/1"))
        .willReturn(WireMock.aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(mapper.writeValueAsString(PocBuilder.buildPocTest()))));
  }
}
