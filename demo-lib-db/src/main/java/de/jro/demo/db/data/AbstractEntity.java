package de.jro.demo.db.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * The abstract default for all entities.
 * 
 * @author Josef Rossa
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = -1L;

  @Column(name = "is_deleted")
  private boolean deleted = false;

  @Column(name = "last_update_date")
  protected Date lastUpdateDate = new Date(System.currentTimeMillis());

  public AbstractEntity() {}

  public AbstractEntity(final long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(final boolean deleted) {
    this.deleted = deleted;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(final Date lastUpdateddAt) {
    this.lastUpdateDate = lastUpdateddAt;
  }
}
