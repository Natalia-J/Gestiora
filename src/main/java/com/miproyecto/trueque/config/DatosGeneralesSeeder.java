package com.miproyecto.trueque.config;

import com.miproyecto.trueque.model.catalogs.*;
import com.miproyecto.trueque.model.enums.*;
import com.miproyecto.trueque.repository.catalog.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatosGeneralesSeeder {

    private final GeneroRepository generoRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final EntidadFederativaRepository entidadFederativaRepository;
    private final IMSSEmployeeRepository imssEmployeeRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final RegimenEmpleadoRepository regimenEmpleadoRepository;
    private final RegimenEmpresaRepository regimenEmpresaRepository;
    private final SBCEmpleadoRepository sbcEmpleadoRepository;
    private final SindicatoEmpleadoRepository sindicatoEmpleadoRepository;
    private final TipoContratoEmpleadoRepository tipoContratoEmpleadoRepository;
    private final TipoCodigoEmpleadoRepository tipoCodigoEmpleadoRepository;
    private final TipoPeriodoRepository tipoPeriodoRepository;
    private final TipoPrestacionRepository tipoPrestacionRepository;
    private final ZonaSalarioRepository zonaSalarioRepository;

    @PostConstruct
    public void cargarDatosGenerales() {
        cargarGeneros();
        cargarEstadosCiviles();
        cargarEntidadesFederativas();
        cargarAvisosIMSS();
        cargarMetodosPago();
        cargarRegimenEmpleado();
        cargarRegimenFiscal();
        cargarSBC();
        cargarSindicato();
        cargarTipoContrato();
        cargarTipoCodigoEmpleado();
        cargarTipoPeriodo();
        cargarTipoPrestacion();
        cargarZonaSalario();
    }

    private void cargarGeneros() {
        if (generoRepository.count() == 0) {
            for (GeneroEnum genero : GeneroEnum.values()) {
                GeneroEmpleado entidad = new GeneroEmpleado();
                entidad.setGenero(genero);
                generoRepository.save(entidad);
            }
        }
    }

    private void cargarEstadosCiviles() {
        if (estadoCivilRepository.count() == 0) {
            for (EstadoCivilEnum estado : EstadoCivilEnum.values()) {
                EstadoCivilEmpleado entidad = new EstadoCivilEmpleado();
                entidad.setEstadoCivilEmpleado(estado);
                estadoCivilRepository.save(entidad);
            }
        }
    }


    private void cargarEntidadesFederativas() {
        if (entidadFederativaRepository.count() == 0) {
            for (EntidadFederativaEnum entidadEnum : EntidadFederativaEnum.values()) {
                EntidadFederativa entidad = new EntidadFederativa();
                entidad.setEntidadFederativa(entidadEnum);
                entidadFederativaRepository.save(entidad);
            }
        }
    }


    private void cargarAvisosIMSS() {
        if (imssEmployeeRepository.count() == 0) {
            for (AvisoIMSSEnum aviso : AvisoIMSSEnum.values()) {
                AvisoIMSSEmpleado entidad = new AvisoIMSSEmpleado();
                entidad.setAvisoImssEmpleado(aviso);
                imssEmployeeRepository.save(entidad);
            }
        }
    }

    private void cargarMetodosPago() {
        if (metodoPagoRepository.count() == 0) {
            for (MetodoPagoEnum metodo : MetodoPagoEnum.values()) {
                MetodoPagoEmpleado entidad = new MetodoPagoEmpleado();
                entidad.setMetodoPago(metodo);
                metodoPagoRepository.save(entidad);
            }
        }
    }

    private void cargarRegimenEmpleado() {
        if (regimenEmpleadoRepository.count() == 0) {
            for (RegimenEmployeeEnum regimen : RegimenEmployeeEnum.values()) {
                RegimenEmployee entidad = new RegimenEmployee();
                entidad.setRegimenFiscalEmployee(regimen);
                regimenEmpleadoRepository.save(entidad);
            }
        }
    }

    private void cargarRegimenFiscal() {
        if (regimenEmpresaRepository.count() == 0) {
            for (RegimenFiscalCompanyEnum regimen : RegimenFiscalCompanyEnum.values()) {
                RegimenFiscal entidad = new RegimenFiscal();
                entidad.setRegimenEmpresa(regimen);
                regimenEmpresaRepository.save(entidad);
            }
        }
    }

    private void cargarSBC() {
        if (sbcEmpleadoRepository.count() == 0) {
            for (SBCEnum sbcEnum : SBCEnum.values()) {
                SBCEmpleado entidad = new SBCEmpleado();
                entidad.setSbc(sbcEnum);
                sbcEmpleadoRepository.save(entidad);
            }
        }
    }


    private void cargarSindicato() {
        if (sindicatoEmpleadoRepository.count() == 0) {
            for (SindicatoEnum sindicato : SindicatoEnum.values()) {
                SindicatoEmpleado entidad = new SindicatoEmpleado();
                entidad.setSindicato(sindicato);
                sindicatoEmpleadoRepository.save(entidad);
            }
        }
    }

    private void cargarTipoContrato() {
        if (tipoContratoEmpleadoRepository.count() == 0) {
            for (TipoContratoEnum contrato : TipoContratoEnum.values()) {
                TipoContratoEmpleado entidad = new TipoContratoEmpleado();
                entidad.setContrato(contrato);
                tipoContratoEmpleadoRepository.save(entidad);
            }
        }
    }

    private void cargarTipoCodigoEmpleado() {
        if (tipoCodigoEmpleadoRepository.count() == 0) {
            for (CodigEmpleEnum codigo : CodigEmpleEnum.values()) {
                TipoCodigoEmpleado entidad = new TipoCodigoEmpleado();
                entidad.setCodigo(codigo);
                tipoCodigoEmpleadoRepository.save(entidad);
            }
        }
    }

    private void cargarTipoPeriodo() {
        if (tipoPeriodoRepository.count() == 0) {
            for (TipoPeriodoEnum periodo : TipoPeriodoEnum.values()) {
                TipoPeriodo entidad = new TipoPeriodo();
                entidad.setPeriodo(periodo);
                tipoPeriodoRepository.save(entidad);
            }
        }
    }

    private void cargarTipoPrestacion() {
        if (tipoPrestacionRepository.count() == 0) {
            for (TipoPrestacionEnum tipo : TipoPrestacionEnum.values()) {
                TipoPrestacionEmpleado entidad = new TipoPrestacionEmpleado();
                entidad.setTipoPrestacionEmpleado(tipo);
                tipoPrestacionRepository.save(entidad);
            }
        }
    }

    private void cargarZonaSalario() {
        if (zonaSalarioRepository.count() == 0) {
            for (ZonaSalarioEnum zona : ZonaSalarioEnum.values()) {
                ZonaSalarioGeneral entidad = new ZonaSalarioGeneral();
                entidad.setZonaSalario(zona);
                zonaSalarioRepository.save(entidad);
            }
        }
    }
}