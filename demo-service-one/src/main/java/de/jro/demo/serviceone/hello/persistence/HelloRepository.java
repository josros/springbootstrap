package de.jro.demo.serviceone.hello.persistence;

import java.util.List;

import de.jro.demo.db.data.AbstractEntityRepository;
import de.jro.demo.serviceone.hello.persistence.data.HelloLanguage;
import de.jro.demo.serviceone.hello.persistence.data.HelloPE;

public interface HelloRepository extends AbstractEntityRepository<HelloPE> {

  public List<HelloPE> findAllByLangAndIsDeletedFalse(HelloLanguage lang);
  
}
