package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import com.grupopan.b2k.pocfeignclient.repositories.poc.PocFeignClientRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PocFeignClientDatasource implements PocFeignClientRepository {

  @Autowired
  private PocFeignClient pocFeignClient;

  @Override
  public PocTeste get(String id) {
    return pocFeignClient.get(id).getBody();
  }

  @Override
  public PocTeste post(Map<String, String> headers, String sortField, PocTeste body) {
    return pocFeignClient.post(headers, sortField, body).getBody();
  }
}
