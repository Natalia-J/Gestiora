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
                manejarPermiso(registro, inconsistencia, justificacion);
                break;
            case ERROR_DE_REGISTRO:
                manejarErrorRegistro(registro);
                break;
            case DIA_FESTIVO_TRABAJADO:
                manejarDiaFestivoTrabajado(registro);
                break;
            case DIA_DE_DESCANSO_LABORADO:
                manejarDiaDescansoLaborado(registro);
                break;
            case OMISION_ENTRADA_O_SALIDA:
                manejarOmisiones(registro);
                break;
            default:
                throw new IllegalArgumentException("Inconsistencia no soportada: " + inconsistencia);
        }
    }

    private void manejarFalta(DiasHoras r, Justificacion justif) {
        r.setHoraEntrada(null);
        r.setHoraSalida(null);
        r.setComentario(InconsistenciasEnum.FALTA.getDescripcion());
        r.setJustificacion(justif);
    }

    private void manejarRetardo(DiasHoras r, Justificacion justif) {
        r.setComentario(InconsistenciasEnum.RETARDO.getDescripcion());
        r.setJustificacion(justif);
    }

    private void manejarSalidaTemprana(DiasHoras r, Justificacion justif) {
        r.setComentario(InconsistenciasEnum.SALIDA_TEMPRANA.getDescripcion());
        r.setJustificacion(justif);
    }

    private void manejarInasistenciaParcial(DiasHoras r, Justificacion justif) {
        r.setComentario("Inasistencia parcial: " + r.getHoraEntrada() + " a " + r.getHoraSalida());
        r.setJustificacion(justif);
    }

    private void manejarPermiso(DiasHoras r, InconsistenciasEnum tipo, Justificacion justif) {
        r.setHoraEntrada(null);
        r.setHoraSalida(null);
        r.setComentario(tipo.getDescripcion());
        r.setJustificacion(justif);
    }

    private void manejarErrorRegistro(DiasHoras r) {
        r.setComentario(InconsistenciasEnum.ERROR_DE_REGISTRO.getDescripcion());
        r.setHoraEntrada(null);
        r.setHoraSalida(null);
    }

    private void manejarDiaFestivoTrabajado(DiasHoras r) {
        r.setComentario(InconsistenciasEnum.DIA_FESTIVO_TRABAJADO.getDescripcion());
    }

    private void manejarDiaDescansoLaborado(DiasHoras r) {
        r.setComentario(InconsistenciasEnum.DIA_DE_DESCANSO_LABORADO.getDescripcion());
    }

    private void manejarOmisiones(DiasHoras r) {
        r.setComentario(InconsistenciasEnum.OMISION_ENTRADA_O_SALIDA.getDescripcion());
        if (r.getHoraEntrada() == null) r.setHoraEntrada(LocalTime.of(9, 0));
        if (r.getHoraSalida() == null) r.setHoraSalida(LocalTime.of(18, 0));
    }

}
