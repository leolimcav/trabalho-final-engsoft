package br.com.ufc.GoBarber.DTO;

import java.util.Date;
import java.util.UUID;

public class ResponseAppointmentsDTO {
  private UUID id;
  private Date date;
  private ResponseUserDTO provider;

  public ResponseAppointmentsDTO() {
  }

  public ResponseAppointmentsDTO(UUID id, Date date, ResponseUserDTO provider) {
    this.id = id;
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

  public ResponseUserDTO getProvider() {
    return provider;
  }

  public void setProvider(ResponseUserDTO provider) {
    this.provider = provider;
  }
}
