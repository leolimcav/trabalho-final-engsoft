package br.com.ufc.GoBarber.DTO;

import java.util.Date;
import java.util.UUID;

public class CreateAppointmentsDTO {
  private Date date;
  private UUID provider;

  public CreateAppointmentsDTO() {
  }

  public CreateAppointmentsDTO(Date date, UUID provider) {
    this.date = date;
    this.provider = provider;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public UUID getProvider() {
    return provider;
  }

  public void setProvider(UUID provider) {
    this.provider = provider;
  }
}
