package br.com.ufc.GoBarber.Services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.GoBarber.Models.Appointments;
import br.com.ufc.GoBarber.Repositories.AppointmentsRepository;

@Service
public class DeleteAppointmentService {

  @Autowired
  private AppointmentsRepository appointmentRepo;

  public void execute(UUID appointmentId) {
    Appointments appointment = appointmentRepo.getOne(appointmentId);
    Date today = new Date();

    if (appointment.getDate().before(new Date())) {
      return;
    }

    if ((appointment.getDate().getHours() - today.getHours()) <= 2) {
      return;
    }

    appointmentRepo.delete(appointment);
  }
}
