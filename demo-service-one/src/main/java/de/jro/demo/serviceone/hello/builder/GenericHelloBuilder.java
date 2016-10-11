package de.jro.demo.serviceone.hello.builder;

public class GenericHelloBuilder<B extends GenericHelloBuilder<B>> {

  protected Long id;
  protected String name;
  protected String language;
  
  @SuppressWarnings("unchecked")
  public B id(Long id) {
    this.id = id;
    return (B) this;
  }
  
  @SuppressWarnings("unchecked")
  public B name(String name) {
    this.name = name;
    return (B) this;
  }
  
  @SuppressWarnings("unchecked")
  public B language(String language) {
    this.language = language;
    return (B) this;
  }
  
}
