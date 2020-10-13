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
    Users userExists = usersRepo.getOne(userId);

    userExists.setName(user.getName());
    userExists.setEmail(user.getEmail());
    userExists.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userExists.setAvatar(user.getAvatar());
    userExists.setIsProvider(user.getIsProvider());

    Users updatedUser = usersRepo.saveAndFlush(userExists);

    return updatedUser;
  }
}
