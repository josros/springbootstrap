package de.jro.demo.db.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This is a repository providing methods related to the {@link AbstractEntity} class. This class
 * should be extended if repository must provide paging and sorting functionality as well.
 *
 * @author Josef Rossa
 *
 * @param <E> The class extending {@link AbstractEntity}
 */
@NoRepositoryBean
public interface PagingAndSortingAbstractEntityRepository<E extends AbstractEntity>
    extends PagingAndSortingRepository<E, Long> {

  List<E> findByIsDeletedTrue();

  Page<E> findByIsDeletedTrue(Pageable pageable);

  List<E> findByIsDeletedFalse();

  Page<E> findByIsDeletedFalse(Pageable pageable);
  
}
