package br.com.ufc.GoBarber.DTO;

import java.util.UUID;

public class ResponseUserDTO {
  private UUID id;
  private String name;
  private String email;
  private String avatar;
  private boolean isProvider;

  public ResponseUserDTO() {
  }

  public ResponseUserDTO(UUID id, String name, String email, String avatar, boolean isProvider) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.avatar = avatar;
    this.isProvider = isProvider;
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

  public boolean getIsProvider() {
    return this.isProvider;
  }

  public void setIsProvider(boolean isProvider) {
    this.isProvider = isProvider;
  }
}
