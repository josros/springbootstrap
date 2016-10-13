package de.jro.demo.serviceone.hello.rest.data;

import javax.validation.constraints.NotNull;

import de.jro.demo.serviceone.hello.HelloLanguage;

public class HelloDTO {

  @NotNull(message = "name must be set.")
  private String name;
  private HelloLanguage language;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public HelloLanguage getLanguage() {
    return language;
  }
  
  public void setLanguage(HelloLanguage language) {
    this.language = language;
  }
  
}
