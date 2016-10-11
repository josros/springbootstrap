package de.jro.demo.serviceone.hello.business.data;

import de.jro.demo.serviceone.hello.builder.GenericHelloBuilder;

public class HelloVoBuilder extends GenericHelloBuilder<HelloVoBuilder> {

  public HelloVO build() {
    return new HelloVO(id, name, language);
  }
  
}
