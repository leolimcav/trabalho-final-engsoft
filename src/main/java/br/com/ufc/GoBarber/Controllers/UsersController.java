package br.com.ufc.GoBarber.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufc.GoBarber.DTO.CreateUserDTO;
import br.com.ufc.GoBarber.DTO.ResponseUserDTO;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.UsersRepository;
import br.com.ufc.GoBarber.Services.CreateUserService;
import br.com.ufc.GoBarber.Services.UpdateUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/users")
public class UsersController {

  @Autowired
  private CreateUserService createUserService;
  @Autowired
  private UpdateUserService updateUserService;

  @Autowired
  private UsersRepository usersRepo;

  @GetMapping("/{userId}")
  public ResponseEntity<ResponseUserDTO> show(@PathVariable UUID userId) {
    Users user = usersRepo.getOne(userId);
    ResponseUserDTO response = new ResponseUserDTO(user.getId(), user.getName(), user.getEmail(), user.getAvatar(),
        user.getIsProvider());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ResponseUserDTO>> index() {
    List<Users> users = usersRepo.findAll();
    List<ResponseUserDTO> response = new ArrayList<>();
    users.forEach(user -> {
      response.add(
          new ResponseUserDTO(user.getId(), user.getName(), user.getEmail(), user.getAvatar(), user.getIsProvider()));
    });

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ResponseUserDTO> create(@RequestBody CreateUserDTO user) {
    Users createdUser = createUserService.execute(user);
    ResponseUserDTO response = new ResponseUserDTO(createdUser.getId(), createdUser.getName(), createdUser.getEmail(),
        createdUser.getAvatar(), user.getIsProvider());
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<ResponseUserDTO> update(@PathVariable UUID userId, @RequestBody CreateUserDTO user) {
    Users updatedUser = updateUserService.execute(userId, user);

    ResponseUserDTO response = new ResponseUserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(),
        updatedUser.getAvatar(), user.getIsProvider());
    return new ResponseEntity<ResponseUserDTO>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{userId}")
  public void delete(@PathVariable UUID userId) {
    usersRepo.deleteById(userId);
  }
}
