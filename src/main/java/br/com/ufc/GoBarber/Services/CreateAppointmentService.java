package br.com.ufc.GoBarber.Services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.GoBarber.DTO.CreateAppointmentsDTO;
import br.com.ufc.GoBarber.Models.Appointments;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.AppointmentsRepository;
import br.com.ufc.GoBarber.Repositories.UsersRepository;

@Service
public class CreateAppointmentService {
  @Autowired
  private UsersRepository userRepo;

  @Autowired
  private AppointmentsRepository appointmentRepo;

  public Appointments execute(CreateAppointmentsDTO appointment) {
    Users provider = userRepo.getOne(appointment.getProvider());
    Date today = new Date();
    if (provider.getIsProvider() == false) {
      return null;
    }

    if (appointment.getDate().before(new Date())) {
      return null;
    }

    if (appointmentRepo.findByDate(appointment.getDate()).size() > 0) {
      return null;
    }

    if (appointment.getDate().equals(today) && ((appointment.getDate().getHours() - today.getHours()) <= 2)) {
      return null;
    }

    Appointments newAppointment = new Appointments(appointment.getDate(), provider);

    Appointments createdAppointment = appointmentRepo.save(newAppointment);

    return createdAppointment;
  }
}
