package com.grupopan.b2k.pocfeignclient.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.grupopan.b2k.pocfeignclient")
public class HexagonalTest {

  @ArchTest
  public static final ArchRule layersValidator = Architectures.layeredArchitecture()
      .layer("Entities").definedBy("..entities..")
      .layer("Interactors").definedBy("..interactors..")
      .layer("Repositories").definedBy("..repositories..")
      .layer("DataSources").definedBy("..datasources..")
      .layer("TransportLayers").definedBy("..transportlayers..")
      .layer("Configs").definedBy("..configs..")
      .whereLayer("Interactors").mayOnlyBeAccessedByLayers("TransportLayers", "Configs")
      .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Interactors", "DataSources", "Configs")
      .whereLayer("DataSources").mayOnlyBeAccessedByLayers("Configs")
      .whereLayer("TransportLayers").mayOnlyBeAccessedByLayers("Configs")
      .whereLayer("Configs").mayNotBeAccessedByAnyLayer();

}
