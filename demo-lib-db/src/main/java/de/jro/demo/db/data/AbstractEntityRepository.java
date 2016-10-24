package de.jro.demo.db.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * This is a repository providing methods related to the {@link AbstractEntity} class.
 *
 * @author Josef Rossa
 *
 * @param <E> The class extending {@link AbstractEntity}
 */
@NoRepositoryBean
public interface AbstractEntityRepository<E extends AbstractEntity>
    extends CrudRepository<E, Long> {

  E findByIdAndDeletedFalse(Long id);
  
  List<E> findByDeletedTrue();

  List<E> findByDeletedFalse();
}
