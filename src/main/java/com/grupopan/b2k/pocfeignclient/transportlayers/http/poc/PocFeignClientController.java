package com.grupopan.b2k.pocfeignclient.transportlayers.http.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import com.grupopan.b2k.pocfeignclient.interactors.poc.PocFeignClientUseCase;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping("/post")
  public ResponseEntity<PocTeste> post(
      @RequestHeader Map<String, String> headers,
      @RequestParam(name = "sortField") String sortField,
      @RequestBody PocTeste body) {

    PocTeste pocTeste = pocFeignClientUseCase.post(headers, sortField, body);

    URI location = URI.create(String.format("/post/%s", pocTeste.getEmail()));

    return ResponseEntity.created(location).build();
  }

}
