package br.com.ufc.GoBarber.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ufc.GoBarber.DTO.CreateUserDTO;
import br.com.ufc.GoBarber.DTO.ResponseUserDTO;
import br.com.ufc.GoBarber.Errors.UserAlreadyExistsException;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.UsersRepository;
import br.com.ufc.GoBarber.Services.CreateUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/users")
public class UsersController {

  @Autowired
  CreateUserService createUserServ;

  @Autowired
  UsersRepository usersRepo;

  @PostMapping
  public ResponseEntity<ResponseUserDTO> create(@RequestBody CreateUserDTO user) {
    Users createdUser = createUserServ.execute(user.getName(), user.getEmail(), user.getPassword(), user.getAvatar());
    ResponseUserDTO responseUser = new ResponseUserDTO(createdUser.getId(), createdUser.getName(),
        createdUser.getEmail(), createdUser.getAvatar());
    return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
  }
}
