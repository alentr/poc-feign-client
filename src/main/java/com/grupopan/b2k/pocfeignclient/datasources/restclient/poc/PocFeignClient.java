package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

import com.grupopan.b2k.pocfeignclient.datasources.restclient.config.poc.PocFeignClientRetryerConfig;
import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pocfeignclient", path = "/poc", url = "${service.test.url}", configuration = PocFeignClientRetryerConfig.class)
public interface PocFeignClient {

  @GetMapping("/get/{id}")
  ResponseEntity<PocTeste> get(@PathVariable String id);

  @PostMapping("/post")
  ResponseEntity<PocTeste> post(
      @RequestHeader Map<String, String> headers,
      @RequestParam(name = "sortField", required = false) String sortField,
      @RequestBody PocTeste body);

}
