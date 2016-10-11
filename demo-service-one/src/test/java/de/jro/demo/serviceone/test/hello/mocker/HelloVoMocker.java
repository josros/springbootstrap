package de.jro.demo.serviceone.test.hello.mocker;

import org.mockito.Mockito;

import de.jro.demo.serviceone.hello.builder.GenericHelloBuilder;
import de.jro.demo.serviceone.hello.business.data.HelloVO;

public class HelloVoMocker extends GenericHelloBuilder<HelloVoMocker> {

  public HelloVO mock() {
    HelloVO mock = Mockito.mock(HelloVO.class);
    Mockito.when(mock.getId()).thenReturn(id);
    Mockito.when(mock.getName()).thenReturn(name);
    Mockito.when(mock.getLanguage()).thenReturn(language);
    return mock;
  }
  
}
