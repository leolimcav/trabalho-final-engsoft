package br.com.ufc.GoBarber.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.GoBarber.DTO.CreateUserDTO;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.UsersRepository;

@Service
public class CreateUserService {

  @Autowired
  private UsersRepository usersRepo;

  public Users execute(CreateUserDTO user) {
    if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
      return null;
    }

    Users emailExists = usersRepo.findByEmail(user.getEmail());

    if (emailExists.getEmail().equals(user.getEmail())) {
      return null;
    }

    if (user.getPassword().length() < 6) {
      return null;
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
    Users newUser = new Users(user.getName(), user.getEmail(), encryptedPassword, user.getAvatar(),
        user.getIsProvider());

    Users createdUser = usersRepo.save(newUser);

    return createdUser;
  }
}
