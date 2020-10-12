package br.com.ufc.GoBarber.Services;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.GoBarber.DTO.CreateUserDTO;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.UsersRepository;

@Service
public class UpdateUserService {

  @Autowired
  private UsersRepository usersRepo;

  public Users execute(UUID userId, CreateUserDTO user) {
    // try {
    Users userExists = usersRepo.getOne(userId);

    // if (userExists.equals(null)) {
    // throw new EntityNotFoundException("User ID not found!");
    // }

    userExists.setName(user.getName());
    userExists.setEmail(user.getEmail());
    userExists.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userExists.setAvatar(user.getAvatar());

    Users updatedUser = usersRepo.saveAndFlush(userExists);

    return updatedUser;
    // } catch (Exception ex) {
    // System.out.println(ex.getMessage());
    // }
    // return null;
  }
}
