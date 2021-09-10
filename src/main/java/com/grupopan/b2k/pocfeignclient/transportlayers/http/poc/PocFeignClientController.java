package com.grupopan.b2k.pocfeignclient.transportlayers.http.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import com.grupopan.b2k.pocfeignclient.interactors.poc.PocFeignClientUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PocFeignClientController {

  @Autowired
  private PocFeignClientUseCase pocFeignClientUseCase;

  @GetMapping("/get/{id}")
  public ResponseEntity<PocTeste> get(@PathVariable String id) {

    PocTeste pocTeste = pocFeignClientUseCase.get(id);

    return ResponseEntity.ok(pocTeste);
  }

}
