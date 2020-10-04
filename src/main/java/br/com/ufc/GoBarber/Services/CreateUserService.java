package br.com.ufc.GoBarber.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.GoBarber.Errors.UserAlreadyExistsException;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.UsersRepository;

@Service
public class CreateUserService {

  @Autowired
  private UsersRepository userRepo;

  public Users execute(String name, String email, String password, String avatar) {
    // try {
    // // if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
    // // throw new Exception("Cannot create an user with empty values!");
    // // }

    // Users emailExists = userRepo.findByEmail(email);

    // if (emailExists.getEmail().equals(email)) {
    // throw new UserAlreadyExistsException("User already exists!");
    // }

    String encryptedPassword = new BCryptPasswordEncoder().encode(password);
    Users user = new Users(name, email, encryptedPassword, avatar);

    Users createdUser = userRepo.save(user);

    return createdUser;
    // } catch (UserAlreadyExistsException err) {
    // System.out.println("Message: " + err.getMessage());
    // }
    // return null;
  }
}
