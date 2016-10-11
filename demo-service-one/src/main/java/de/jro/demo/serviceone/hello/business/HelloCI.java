package de.jro.demo.serviceone.hello.business;

import java.util.List;

import de.jro.demo.serviceone.hello.business.data.HelloVO;

public interface HelloCI {

  public Long create(final HelloVO hello);
  
  public HelloVO readOne(final Long id);
  
  public List<HelloVO> readAll(final String language);
  
  public void update(final Long id, final HelloVO hello);
  
  public void delete(final Long id);
  
  
}
