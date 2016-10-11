package de.jro.demo.serviceone.hello.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.jro.demo.serviceone.hello.business.HelloCI;
import de.jro.demo.serviceone.hello.business.data.HelloVO;
import de.jro.demo.serviceone.hello.business.data.HelloVoBuilder;
import de.jro.demo.serviceone.hello.rest.data.HelloDTO;
import de.jro.demo.serviceone.hello.rest.data.HelloLanguageDTO;
import de.jro.demo.serviceone.hello.rest.data.HelloWithIdDTO;
import de.jro.demo.web.defaults.AbstractRestController;

@RestController
@RequestMapping("/hello")
public class HelloRC extends AbstractRestController {

  private static final Logger log = LoggerFactory.getLogger(HelloRC.class);
  
  @Autowired
  private HelloCI helloCi;
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<HelloWithIdDTO> get(@PathVariable final Long id) {
    log.debug(createGenericLogString("get", "id", String.valueOf(id)));
    HelloVO vo = helloCi.readOne(id);
    return respondOk(convertToDto(vo));
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<HelloWithIdDTO>> getAll(
      @RequestParam(required = false) final HelloLanguageDTO language) {
    log.debug(createGenericLogString("getAll", "language", String.valueOf(language)));
    List<HelloVO> vos = helloCi.readAll(language != null ? language.name() : null);
    return respondOk(convertList(vos));
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Long> post(@Validated @RequestBody final HelloDTO hello) {
    log.debug(createGenericLogString("post", "hello", String.valueOf(hello)));
    Long id = helloCi.create(convertToVo(hello));
    return respond(id, HttpStatus.CREATED);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> put(
      @PathVariable final Long id,
      @Validated @RequestBody final HelloDTO hello) {
    log.debug(createGenericLogString("put", 
        "id", String.valueOf(id), "hello", String.valueOf(hello)));
    helloCi.update(id, convertToVo(hello));
    return respondOkVoid();
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    log.debug(createGenericLogString("delete", "id", String.valueOf(id)));
    helloCi.delete(id);
    return respondOkVoid();
  }
  
  
  private List<HelloWithIdDTO> convertList(List<HelloVO> vos) {
    return vos.stream().map(vo -> convertToDto(vo)).collect(Collectors.toList());
  }
  
  private HelloWithIdDTO convertToDto(HelloVO vo) {
    HelloWithIdDTO dto = new HelloWithIdDTO();
    dto.setId(vo.getId());
    dto.setLanguage(HelloLanguageDTO.valueOf(vo.getLanguage()));
    dto.setName(vo.getName());
    return dto;
  }
  
  private HelloVO convertToVo(HelloDTO dto) {
    HelloVoBuilder vob = new HelloVoBuilder();
    if (dto instanceof HelloWithIdDTO) {
      vob.id(((HelloWithIdDTO) dto).getId());
    }
    return vob.name(dto.getName())
        .language(dto.getLanguage().name()).build();
  }
  
}
