package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pocfeignclient", url = "${service.test.url}")
public interface PocFeignClient {

  @GetMapping("/get/{id}")
  ResponseEntity<PocTeste> get(@PathVariable String id);

}
