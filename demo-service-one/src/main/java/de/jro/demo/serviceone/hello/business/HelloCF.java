package de.jro.demo.serviceone.hello.business;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jro.demo.serviceone.hello.business.data.HelloVO;
import de.jro.demo.serviceone.hello.business.data.HelloVoBuilder;
import de.jro.demo.serviceone.hello.persistence.HelloRepository;
import de.jro.demo.serviceone.hello.persistence.data.HelloLanguage;
import de.jro.demo.serviceone.hello.persistence.data.HelloPE;

@Service
public class HelloCF implements HelloCI {

  @Autowired
  private HelloRepository helloRepository;
  
  @Override
  public Long create(HelloVO hello) {
    HelloPE pe = convertToPe(hello);
    pe = helloRepository.save(pe);
    return pe.getId();
  }

  @Override
  public HelloVO readOne(Long id) {
    HelloPE pe = findExistingElement(id);
    return convertToVo(pe);
  }

  @Override
  public List<HelloVO> readAll(String language) {
    List<HelloPE> hellos = null;
    if (language != null) {
      hellos = helloRepository.findAllByLangAndIsDeletedFalse(
          HelloLanguage.valueOf(language));
    } else {
      hellos = helloRepository.findByIsDeletedFalse();
    }
    return convertAll(hellos);
  }

  @Override
  public void update(Long id, HelloVO hello) {
    HelloPE pe = findExistingElement(id);
    updatePeAttributes(pe, hello);
    helloRepository.save(pe);
  }

  @Override
  public void delete(Long id) {
    HelloPE pe = findExistingElement(id);
    pe.setDeleted(true);
    pe.setLastUpdateDate(new Date(System.currentTimeMillis()));
    helloRepository.save(pe);
  }
  
  private List<HelloVO> convertAll(List<HelloPE> pes) {
    return pes.stream().map(pe -> convertToVo(pe)).collect(Collectors.toList());
  }
  
  private HelloPE findExistingElement(Long id) {
    HelloPE pe = helloRepository.findByIdAndIsDeletedFalse(id);
    if (pe == null) {
      throw new NoSuchElementException("No element with id: " + id);
    }
    return pe;
  }
  
  private HelloVO convertToVo(HelloPE pe) {
    HelloVoBuilder vob = new HelloVoBuilder();
    return vob.id(pe.getId())
        .language(pe.getLang().name()).name(pe.getName()).build();
  }
  
  private HelloPE convertToPe(HelloVO vo) {
    HelloPE pe = new HelloPE();
    updatePeAttributes(pe, vo);
    return pe;
  }
  
  private void updatePeAttributes(HelloPE pe, HelloVO vo) {
    pe.setLang(HelloLanguage.valueOf(vo.getLanguage()));
    pe.setName(vo.getName());
    pe.setLastUpdateDate(new Date(System.currentTimeMillis()));
  }

  
  
}
