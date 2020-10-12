package br.com.ufc.GoBarber.Repositories;

import org.springframework.stereotype.Repository;

import br.com.ufc.GoBarber.Models.Users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
  Users findByEmail(String email);
}