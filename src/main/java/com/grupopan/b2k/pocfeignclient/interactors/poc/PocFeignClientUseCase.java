package com.grupopan.b2k.pocfeignclient.interactors.poc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import com.grupopan.b2k.pocfeignclient.interactors.InteractorException;
import com.grupopan.b2k.pocfeignclient.repositories.FeignException;
import com.grupopan.b2k.pocfeignclient.repositories.poc.PocFeignClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PocFeignClientUseCase {

  @Autowired
  private PocFeignClientRepository pocFeignClientRepository;

  @Autowired
  private ObjectMapper mapper;

  public PocTeste get(String id) {
    try {
      return pocFeignClientRepository.get(id);
    } catch (FeignException feignException) {
      if (feignException.getHttpCode().value() == HttpStatus.PRECONDITION_FAILED.value()) {
        try {
          return feignException.getBody() != null ? mapper.readValue(feignException.getBody(), PocTeste.class) : null;
        } catch (JsonProcessingException e) {
          throw new InteractorException(e.getMessage());
        }
      }
      throw feignException;
    }
  }
}
