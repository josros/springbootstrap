package de.jro.demo.serviceone.test.hello.rest;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.jro.demo.serviceone.hello.HelloLanguage;
import de.jro.demo.serviceone.hello.business.HelloCI;
import de.jro.demo.serviceone.hello.business.data.HelloVO;
import de.jro.demo.serviceone.hello.rest.HelloRC;
import de.jro.demo.serviceone.test.hello.mocker.HelloVoMocker;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@SpringApplicationConfiguration(HelloRCTest.class)
@WebAppConfiguration
public class HelloRCTest {

  @InjectMocks
  private HelloRC rc;

  @Mock
  private HelloCI ciMock;

  private MockMvc mockMvc;


  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(rc).build();
  }
  
  @Test
  public void testGet() throws Exception {
    Long id = 1L;
    HelloVO mock = new HelloVoMocker().id(id).name("Hans").language(HelloLanguage.DE).mock();
    Mockito.when(ciMock.readOne(id)).thenReturn(mock);
    
    final String uri = "/hello/" + id;
    mockMvc.perform(MockMvcRequestBuilders.get(uri)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id",  Matchers.is(Math.toIntExact(id))))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name",  Matchers.is("Hans")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.language",  Matchers.is("DE")));
  }
  
  @Test
  public void testGetAll() throws Exception {
    HelloVO mock1 = new HelloVoMocker().id(1L).name("Hans").language(HelloLanguage.DE).mock();
    HelloVO mock2 = new HelloVoMocker().id(2L).name("Peter").language(HelloLanguage.DE).mock();
    List<HelloVO> hellos = Arrays.asList(mock1, mock2);
    
    Mockito.when(ciMock.readAll("DE")).thenReturn(hellos);
    final String uri = "/hello?language=DE";
    
    mockMvc.perform(MockMvcRequestBuilders.get(uri)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",  Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",  Matchers.is("Hans")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].language",  Matchers.is("DE")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id",  Matchers.is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",  Matchers.is("Peter")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].language",  Matchers.is("DE")));
  }
  
  @Test
  public void testPost() throws Exception {
    Long id = 3L;
    Mockito.when(ciMock.create(
        org.mockito.Matchers.any(HelloVO.class))).thenReturn(id);
    
    String body =  "{"
                  + "\"language\": \"DE\","
                  + "\"name\": \"Johann\""
                  + "}";
    
    final String uri = "/hello";
    mockMvc.perform(
        MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(Math.toIntExact(id))));
  }
  
  @Test
  public void testPut() throws Exception {
    Long id = 3L;
    String body =  "{"
        + "\"id\": \"" + id + "\","
        + "\"language\": \"EN\","
        + "\"name\": \"Johann\""
        + "}";
    
    final String uri = "/hello/" + id;
    mockMvc.perform(
        MockMvcRequestBuilders.put(uri)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(body))
        .andExpect(MockMvcResultMatchers.status().isOk());
    
    final ArgumentCaptor<HelloVO> captor =
        ArgumentCaptor.forClass(HelloVO.class);

    Mockito.verify(ciMock, Mockito.times(1))
            .update(org.mockito.Matchers.eq(id), captor.capture());
    
    final HelloVO hello = captor.getValue();
    Assert.assertEquals(HelloLanguage.EN, hello.getLanguage());
    Assert.assertEquals("Johann", hello.getName());
  }
  
  @Test
  public void testDelete() throws Exception {
    Long id = 4L;
    
    final String uri = "/hello/" + id;
    mockMvc.perform(
        MockMvcRequestBuilders.delete(uri))
        .andExpect(MockMvcResultMatchers.status().isOk());
    
    Mockito.verify(ciMock, Mockito.times(1))
            .delete(org.mockito.Matchers.eq(id));
  }
  
  
}
