package com.grupopan.b2k.pocfeignclient.datasources.restclient.poc;

import com.grupopan.b2k.pocfeignclient.entities.poc.PocTeste;

public class PocBuilder {

  public static PocTeste buildPocTest() {
    return new PocTeste("Alexandre Telles", "alexandre.telles@dextra-sw.com");
  }

}
