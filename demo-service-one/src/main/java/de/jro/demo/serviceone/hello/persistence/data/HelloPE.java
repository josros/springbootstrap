package de.jro.demo.serviceone.hello.persistence.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.jro.demo.db.data.AbstractEntity;

@Entity(name = "Hello")
public class HelloPE extends AbstractEntity {

  @Column(name = "name")
  private String name;
  
  @Column(name = "language")
  @Enumerated(EnumType.STRING)
  private HelloLanguage lang;
  
  public HelloPE() {}
  
  public HelloPE(Long id) {
    super(id);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HelloLanguage getLang() {
    return lang;
  }

  public void setLang(HelloLanguage lang) {
    this.lang = lang;
  }
  
}
