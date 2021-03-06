package com.grupopan.b2k.pocfeignclient.repositories.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;
import java.util.Map;

public interface PocFeignClientRepository {

  PocTeste get(String id);

  PocTeste post(Map<String, String> headers, String sortField, PocTeste body);

}
