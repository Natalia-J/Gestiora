package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.TurnoRequest;
import com.miproyecto.trueque.dto.TurnoResponse;
import com.miproyecto.trueque.model.Turno;
import com.miproyecto.trueque.model.catalogs.DiaSemana;
import com.miproyecto.trueque.model.catalogs.TipoJornada;
import com.miproyecto.trueque.repository.TurnoRepository;
import com.miproyecto.trueque.repository.catalog.DiaSemanaRepository;
import com.miproyecto.trueque.repository.catalog.TipoJornadaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final TipoJornadaRepository tipoJornadaRepository;
    private final DiaSemanaRepository diaSemanaRepository;

    public TurnoService(TurnoRepository turnoRepository, TipoJornadaRepository tipoJornadaRepository, DiaSemanaRepository diaSemanaRepository) {
        this.turnoRepository = turnoRepository;
        this.tipoJornadaRepository = tipoJornadaRepository;
        this.diaSemanaRepository=diaSemanaRepository;
    }

    public TurnoResponse crearTurnoDesdeRequest(TurnoRequest request) {
        TipoJornada tipoJornada = tipoJornadaRepository.findById(request.getTipoJornada())
                .orElseThrow(() -> new IllegalArgumentException("TipoJornada no encontrado con ID: " + request.getTipoJornada()));

        LocalTime horaEntrada = LocalTime.parse(request.getHoraEntrada());
        LocalTime horaSalida = LocalTime.parse(request.getHoraSalida());

        Duration duration;
        if (horaSalida.isBefore(horaEntrada)) {
            duration = Duration.between(horaEntrada, LocalTime.MIDNIGHT)
                    .plus(Duration.between(LocalTime.MIDNIGHT, horaSalida));
        } else {
            duration = Duration.between(horaEntrada, horaSalida);
        }

        double horasTurno = duration.toMinutes() / 60.0;

        Set<DiaSemana> diasDescanso = new HashSet<>(diaSemanaRepository.findAllById(request.getDiasDescanso()));
        if(diasDescanso.size() != request.getDiasDescanso().size()){
            throw new IllegalArgumentException("Uno o más días de descanso no existen");
        }

        Turno turno = new Turno();
        turno.setNombre(request.getNombreTurno());
        turno.setHoraEntrada(request.getHoraEntrada());
        turno.setHoraSalida(request.getHoraSalida());
        turno.setHorasTurno(horasTurno);
        turno.setTipoJornada(tipoJornada);
        turno.setDiasDescanso(diasDescanso);

        turnoRepository.save(turno);

        TurnoResponse response = new TurnoResponse();
        response.setNombreTurno(turno.getNombre());
        response.setHoraEntrada(turno.getHoraEntrada());
        response.setHoraSalida(turno.getHoraSalida());
        response.setHorasTurno(turno.getHorasTurno());
        response.setTipoJornada(tipoJornada.getTipoJornada().getDescripcion());

        response.setDiasDescanso(
                turno.getDiasDescanso()
                        .stream()
                        .map(d -> d.getDiaSemana().getDescripcion())
                        .collect(Collectors.toSet())
        );



        return response;
    }


    public List<Turno> obtenerTodos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> obtenerPorNombre(String nombre) {
        return turnoRepository.findByNombre(nombre);
    }

    public void eliminarPorNombre(String nombre) {
        turnoRepository.deleteByNombre(nombre);
    }

    public boolean existePorNombre(String nombre) {
        return turnoRepository.existsByNombre(nombre);
    }

    public List<TurnoResponse> obtenerTodosResponse() {
        return turnoRepository.findAll()
                .stream()
                .map(this::mapearATurnoResponse)
                .toList();
    }

    public Optional<TurnoResponse> obtenerPorNombreResponse(String nombre) {
        return turnoRepository.findByNombre(nombre)
                .map(this::mapearATurnoResponse);
    }

    private TurnoResponse mapearATurnoResponse(Turno turno) {
        TurnoResponse response = new TurnoResponse();
        response.setNombreTurno(turno.getNombre());
        response.setHoraEntrada(turno.getHoraEntrada());
        response.setHoraSalida(turno.getHoraSalida());
        response.setHorasTurno(turno.getHorasTurno());
        response.setTipoJornada(turno.getTipoJornada().getTipoJornada().getDescripcion());
        response.setDiasDescanso(turno.getDiasDescanso().stream()
                .map(d -> d.getDiaSemana().getDescripcion())
                .collect(Collectors.toSet()));
        return response;
    }

}
