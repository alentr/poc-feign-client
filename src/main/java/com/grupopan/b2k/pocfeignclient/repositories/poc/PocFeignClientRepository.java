package com.grupopan.b2k.pocfeignclient.repositories.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;

public interface PocFeignClientRepository {

  PocTeste get(String id);

}
