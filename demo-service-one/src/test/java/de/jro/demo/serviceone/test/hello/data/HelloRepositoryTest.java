package de.jro.demo.serviceone.test.hello.data;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jro.demo.serviceone.hello.HelloLanguage;
import de.jro.demo.serviceone.hello.persistence.HelloRepository;
import de.jro.demo.serviceone.hello.persistence.data.HelloPE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HelloRepositoryTestConfig.class})
@Sql("delete from hello")
public class HelloRepositoryTest {

  @Autowired
  private HelloRepository helloRepository;
  
  @After
  public void cleanUp() {
    helloRepository.deleteAll();
  }
  
  @Test
  @Sql("/test-createHellos.sql")
  public void testFindAllByLangAndIsDeletedFalse() {
    List<HelloPE> pesFound = helloRepository
        .findAllByLangAndIsDeletedFalse(HelloLanguage.DE);
    Assert.assertEquals(2, pesFound.size());
    Assert.assertTrue(pesFound.stream().anyMatch(h -> h.getName().equals("Hans")));
    Assert.assertTrue(pesFound.stream().anyMatch(h -> h.getName().equals("Johann")));
  }
  
}
