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
  private boolean isDeleted = false;

  @Column(name = "last_update_date")
  protected Date lastUpdateDate = new Date(System.currentTimeMillis());

  public AbstractEntity() {}

  public AbstractEntity(final long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(final boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(final Date lastUpdateddAt) {
    this.lastUpdateDate = lastUpdateddAt;
  }
}
