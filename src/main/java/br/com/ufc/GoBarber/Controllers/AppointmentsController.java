package br.com.ufc.GoBarber.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufc.GoBarber.DTO.CreateAppointmentsDTO;
import br.com.ufc.GoBarber.DTO.ResponseAppointmentsDTO;
import br.com.ufc.GoBarber.DTO.ResponseUserDTO;
import br.com.ufc.GoBarber.Models.Appointments;
import br.com.ufc.GoBarber.Models.Users;
import br.com.ufc.GoBarber.Repositories.AppointmentsRepository;
import br.com.ufc.GoBarber.Services.CreateAppointmentService;
import br.com.ufc.GoBarber.Services.DeleteAppointmentService;
import br.com.ufc.GoBarber.Services.UpdateAppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

  @Autowired
  private AppointmentsRepository appointmentsRepo;

  @Autowired
  private CreateAppointmentService createAppointmentService;

  @Autowired
  private UpdateAppointmentService updateAppointmentService;

  @Autowired
  private DeleteAppointmentService deleteAppointmentService;

  @GetMapping
  public ResponseEntity<List<ResponseAppointmentsDTO>> index() {
    List<Appointments> appointments = appointmentsRepo.findAll();
    List<ResponseAppointmentsDTO> response = new ArrayList<>();
    appointments.forEach(appointment -> {
      ResponseUserDTO provider = new ResponseUserDTO(appointment.getProvider().getId(),
          appointment.getProvider().getName(), appointment.getProvider().getEmail(),
          appointment.getProvider().getAvatar(), appointment.getProvider().getIsProvider());
      response.add(new ResponseAppointmentsDTO(appointment.getId(), appointment.getDate(), provider));
    });
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{appointmentId}")
  public ResponseEntity<ResponseAppointmentsDTO> show(@PathVariable UUID appointmentId) {
    Appointments appointment = appointmentsRepo.getOne(appointmentId);
    Users providerUser = appointment.getProvider();
    ResponseUserDTO provider = new ResponseUserDTO(providerUser.getId(), providerUser.getName(),
        providerUser.getEmail(), providerUser.getAvatar(), providerUser.getIsProvider());
    ResponseAppointmentsDTO response = new ResponseAppointmentsDTO(appointment.getId(), appointment.getDate(),
        provider);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ResponseAppointmentsDTO> create(@RequestBody CreateAppointmentsDTO appointment) {
    Appointments newAppointment = createAppointmentService.execute(appointment);
    ResponseUserDTO provider = new ResponseUserDTO(newAppointment.getProvider().getId(),
        newAppointment.getProvider().getName(), newAppointment.getProvider().getEmail(),
        newAppointment.getProvider().getAvatar(), newAppointment.getProvider().getIsProvider());

    ResponseAppointmentsDTO response = new ResponseAppointmentsDTO(newAppointment.getId(), newAppointment.getDate(),
        provider);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/{appointmentId}")
  public ResponseEntity<ResponseAppointmentsDTO> update(@PathVariable UUID appointmentId,
      @RequestBody CreateAppointmentsDTO appointment) {
    Appointments updatedAppointment = updateAppointmentService.execute(appointmentId, appointment);
    Users appointmentProvider = updatedAppointment.getProvider();
    ResponseUserDTO provider = new ResponseUserDTO(appointmentProvider.getId(), appointmentProvider.getName(),
        appointmentProvider.getEmail(), appointmentProvider.getAvatar(), appointmentProvider.getIsProvider());
    ResponseAppointmentsDTO response = new ResponseAppointmentsDTO(updatedAppointment.getId(),
        updatedAppointment.getDate(), provider);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{appointmentId}")
  public ResponseEntity<String> delete(@PathVariable UUID appointmentId) {
    deleteAppointmentService.execute(appointmentId);

    return new ResponseEntity<>("Appointment Removed", HttpStatus.OK);
  }

}
