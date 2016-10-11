package de.jro.demo.serviceone.hello.persistence.data;

public enum HelloLanguage {

  DE("Hallo"), EN("Hello"), ES("Hola");
  
  private String hello;
  
  private HelloLanguage(String hello) {
    this.hello = hello;
  }
  
  public String getHello() {
    return hello;
  }
  
}
