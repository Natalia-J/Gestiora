package com.miproyecto.trueque.service;

import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.catalogs.Justificacion;
import com.miproyecto.trueque.model.enums.InconsistenciasEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Setter
@Getter
@Service
public class InconsistenciasService {

    public void procesarInconsistencia(
            DiasHoras registro,
            InconsistenciasEnum inconsistencia,
            Justificacion justificacion
    ) {
        if (registro == null || inconsistencia == null) {
            throw new IllegalArgumentException("Registro o inconsistencia no pueden ser nulos");
        }

        registro.setComentario(inconsistencia.getDescripcion());

        switch (inconsistencia) {
            case FALTA:
                manejarFalta(registro, justificacion);
                break;
            case RETARDO:
                manejarRetardo(registro, justificacion);
                break;
            case SALIDA_TEMPRANA:
                manejarSalidaTemprana(registro, justificacion);
                break;
            case INASISTENCIA_PARCIAL:
                manejarInasistenciaParcial(registro, justificacion);
                break;
            case PERMISO_CON_GOCE:
            case PERMISO_SIN_GOCE:
                manejarPermiso(registro, justificacion);
                break;
            case ERROR_DE_REGISTRO:
                manejarErrorRegistro(registro);
                break;
            case DIA_FESTIVO_TRABAJADO:
                break;
            case DIA_DE_DESCANSO_LABORADO:
                break;
            default:
                throw new IllegalArgumentException("Inconsistencia no soportada: " + inconsistencia);
        }
    }

    private void manejarFalta(DiasHoras r, Justificacion justif) {
        r.setHoraEntrada(null);
        r.setHoraSalida(null);
    }

    private void manejarRetardo(DiasHoras r, Justificacion justif) {
    }

    private void manejarSalidaTemprana(DiasHoras r, Justificacion justif) {
    }

    private void manejarInasistenciaParcial(DiasHoras r, Justificacion justif) {
        String entrada = r.getHoraEntrada() != null ? r.getHoraEntrada().toString() : "sin entrada";
        String salida = r.getHoraSalida() != null ? r.getHoraSalida().toString() : "sin salida";
        r.setComentario("Inasistencia parcial: " + entrada + " a " + salida);
    }

    private void manejarPermiso(DiasHoras r, Justificacion justif) {
        r.setHoraEntrada(null);
        r.setHoraSalida(null);
    }

    private void manejarErrorRegistro(DiasHoras r) {
        r.setHoraEntrada(null);
        r.setHoraSalida(null);
    }
}
