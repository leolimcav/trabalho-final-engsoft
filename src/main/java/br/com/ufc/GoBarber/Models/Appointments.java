package br.com.ufc.GoBarber.Models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointments")
public class Appointments {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "date", nullable = false)
  @DateTimeFormat(pattern = "dd/MM/yyyy hh:MM")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Users.class)
  private Users provider;

  public Appointments() {
  }

  public Appointments(Date date, Users provider) {
    this.date = date;
    this.provider = provider;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Users getProvider() {
    return provider;
  }

  public void setProvider(Users provider) {
    this.provider = provider;
  }
}
