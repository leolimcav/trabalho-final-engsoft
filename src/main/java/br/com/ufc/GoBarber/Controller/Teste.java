package br.com.ufc.GoBarber.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufc.GoBarber.Model.User;

@RestController
public class Teste {

  @GetMapping("/")
  public User index() {
    return new User("leonardo", "leonardo@gmail.com");
  }

}