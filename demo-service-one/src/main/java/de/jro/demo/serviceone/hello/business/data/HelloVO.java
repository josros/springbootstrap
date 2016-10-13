package de.jro.demo.serviceone.hello.business.data;

import de.jro.demo.serviceone.hello.HelloLanguage;

public class HelloVO {

  private final Long id;
  private final String name;
  private final HelloLanguage language;
  
  public HelloVO(String name, HelloLanguage language) {
    super();
    this.id = null;
    this.name = name;
    this.language = language;
  }
  
  public HelloVO(Long id, String name, HelloLanguage language) {
    super();
    this.id = id;
    this.name = name;
    this.language = language;
  }

  public Long getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }

  public HelloLanguage getLanguage() {
    return language;
  }
  
}
