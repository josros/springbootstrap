package de.jro.demo.serviceone.test.hello.business;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.jro.demo.serviceone.hello.HelloLanguage;
import de.jro.demo.serviceone.hello.business.HelloCF;
import de.jro.demo.serviceone.hello.business.data.HelloVO;
import de.jro.demo.serviceone.hello.persistence.HelloRepository;
import de.jro.demo.serviceone.hello.persistence.data.HelloPE;
import de.jro.demo.serviceone.test.hello.mocker.HelloVoMocker;

public class HelloCITest {

  @Mock
  private HelloRepository repo;
  
  @InjectMocks
  private HelloCF cf;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testCreate() {
    HelloPE pe = mockHelloPe(1L, null, null);
    Mockito.when(repo.save(Matchers.any(HelloPE.class))).thenReturn(pe);
    
    HelloVO mockVo = new HelloVoMocker().language(HelloLanguage.DE).name("Peter").mock();
    
    final ArgumentCaptor<HelloPE> captor = ArgumentCaptor
        .forClass(HelloPE.class);
    
    cf.create(mockVo);
    
    Mockito.verify(repo, Mockito.times(1)).save(captor.capture());
    HelloPE value = captor.getValue();
    
    Assert.assertEquals(-1L, value.getId());
    Assert.assertEquals("Peter", value.getName());
    Assert.assertEquals(HelloLanguage.DE, value.getLang());
    Assert.assertNotNull(value.getLastUpdateDate());
    Assert.assertFalse(value.isDeleted());
  }
  
  @Test
  public void testReadOne() {
    Long id = 1L;
    HelloPE mockPe = mockHelloPe(id, HelloLanguage.DE, "Johann");
    Mockito.when(repo.findByIdAndDeletedFalse(id)).thenReturn(mockPe);
    
    HelloVO vo = cf.readOne(id);
    
    Assert.assertEquals(id, vo.getId());
    Assert.assertEquals(HelloLanguage.DE, vo.getLanguage());
    Assert.assertEquals("Johann", vo.getName());
  }
  
  @Test
  public void testReadAll() {
    HelloPE mockPe1 = mockHelloPe(1L, HelloLanguage.DE, "Johann");
    HelloPE mockPe2 = mockHelloPe(2L, HelloLanguage.DE, "Gerd");
    List<HelloPE> mocks = Arrays.asList(mockPe1, mockPe2);
    Mockito.when(repo.findAllByLangAndDeletedFalse(HelloLanguage.DE)).thenReturn(mocks);
    
    List<HelloVO> vos = cf.readAll("DE");
    
    Assert.assertEquals(2, vos.size());
    Assert.assertTrue(vos.stream().anyMatch(h -> h.getName().equals("Johann")));
    Assert.assertTrue(vos.stream().anyMatch(h -> h.getName().equals("Gerd")));
  }
  
  @Test
  public void testUpdate() {
    Long id = 5L;
    HelloPE spyPe = spyHelloPe(id, HelloLanguage.DE, "Johann");
    Mockito.when(repo.findByIdAndDeletedFalse(id)).thenReturn(spyPe);
    
    HelloVO mockVo = new HelloVoMocker().name("Gerd").language(HelloLanguage.EN).mock();
    
    cf.update(id, mockVo);
    
    Mockito.verify(spyPe).setName("Gerd");
    Mockito.verify(spyPe).setLang(HelloLanguage.EN);    
  }
  
  @Test
  public void testDelete() {
    Long id = 7L;
    HelloPE spyPe = spyHelloPe(id, HelloLanguage.DE, "Johann");
    Mockito.when(repo.findByIdAndDeletedFalse(id)).thenReturn(spyPe);
    
    cf.delete(id);
    
    Mockito.verify(spyPe).setDeleted(true);
    Mockito.verify(repo, Mockito.never()).delete(Mockito.any(HelloPE.class));
    Mockito.verify(repo, Mockito.times(1)).save(Mockito.any(HelloPE.class));
  }
  
  
  private HelloPE mockHelloPe(Long id, HelloLanguage lang, String name) {
    HelloPE pe = Mockito.mock(HelloPE.class);
    Mockito.when(pe.getId()).thenReturn(id);
    Mockito.when(pe.getLang()).thenReturn(lang);
    Mockito.when(pe.getName()).thenReturn(name);
    return pe;
  }
  
  private HelloPE spyHelloPe(Long id, HelloLanguage lang, String name) {
    HelloPE pe = Mockito.spy(HelloPE.class);
    Mockito.doReturn(id).when(pe).getId();
    Mockito.doReturn(lang).when(pe).getLang();
    Mockito.doReturn(name).when(pe).getName();
    return pe;
  }
  
}
