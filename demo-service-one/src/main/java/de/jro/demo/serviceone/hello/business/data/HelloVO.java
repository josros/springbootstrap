package de.jro.demo.serviceone.hello.business.data;

public class HelloVO {

  private final Long id;
  private final String name;
  private final String language;
  
  public HelloVO(String name, String language) {
    super();
    this.id = null;
    this.name = name;
    this.language = language;
  }
  
  public HelloVO(Long id, String name, String language) {
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

  public String getLanguage() {
    return language;
  }
  
}
