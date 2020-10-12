package br.com.ufc.GoBarber.DTO;

import java.util.UUID;

public class ResponseUserDTO {
  private UUID id;
  private String name;
  private String email;
  private String avatar;

  public ResponseUserDTO() {
  }

  public ResponseUserDTO(UUID id, String name, String email, String avatar) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.avatar = avatar;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getAvatar() {
    return avatar;
  }
}
