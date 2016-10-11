package de.jro.demo.serviceone.hello.rest.data;

import javax.validation.constraints.NotNull;

public class HelloDTO {

  @NotNull(message = "name must be set.")
  private String name;
  private HelloLanguageDTO language;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public HelloLanguageDTO getLanguage() {
    return language;
  }
  
  public void setLanguage(HelloLanguageDTO language) {
    this.language = language;
  }
  
}
