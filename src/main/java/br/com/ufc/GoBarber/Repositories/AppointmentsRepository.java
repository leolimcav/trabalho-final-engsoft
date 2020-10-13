package br.com.ufc.GoBarber.Repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.GoBarber.Models.Appointments;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, UUID> {
  List<Appointments> findByDate(Date date);

  List<Appointments> findByProvider(UUID provider);
}
