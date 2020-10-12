package br.com.ufc.GoBarber.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User Already Exists!")
public class UserAlreadyExistsException extends Exception {
  private static final long serialVersionUID = -7467404463892927886L;

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
