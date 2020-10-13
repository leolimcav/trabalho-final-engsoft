package br.com.ufc.GoBarber.Services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.GoBarber.DTO.CreateAppointmentsDTO;
import br.com.ufc.GoBarber.Models.Appointments;
import br.com.ufc.GoBarber.Repositories.AppointmentsRepository;

@Service
public class UpdateAppointmentService {

  @Autowired
  private AppointmentsRepository appointmentRepo;

  public Appointments execute(UUID appointmentId, CreateAppointmentsDTO appointment) {
    Appointments findAppointment = appointmentRepo.getOne(appointmentId);

    if (appointment.getDate().before(findAppointment.getDate())) {
      return null;
    }

    if (appointment.getDate().before(new Date())) {
      return null;
    }

    if ((appointment.getDate().getHours() - findAppointment.getDate().getHours()) <= 2) {
      return null;
    }

    findAppointment.setDate(appointment.getDate());

    Appointments updatedAppointment = appointmentRepo.saveAndFlush(findAppointment);

    return updatedAppointment;
  }
}
