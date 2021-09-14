package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.grupopan.b2k.pocfeignclient.config.WireMockConfig;
import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
class PocFeignClientTest {

  @Autowired
  private WireMockServer wireMockServer;

  @Autowired
  private PocFeignClient pocFeignClient;

  @BeforeEach
  void setUp() throws IOException {
    PocMocks.setupGetPocResponse(wireMockServer);
  }

  @Test
  public void quandoConsultarGet_DeveRetornarObjetoPoc() {
    final PocTeste objectTest = PocBuilder.buildPocTest();
    final PocTeste pocTeste = pocFeignClient.get("1").getBody();

    assertTrue(Objects.nonNull(pocTeste));
    assertTrue(pocTeste.getNome().equals(objectTest.getNome()));
    assertTrue(pocTeste.getEmail().equals(objectTest.getEmail()));
  }
}