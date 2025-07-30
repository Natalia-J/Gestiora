package com.miproyecto.trueque.config;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.*;
import com.miproyecto.trueque.model.enums.*;
import com.miproyecto.trueque.repository.EmpresaRepository;
import com.miproyecto.trueque.repository.catalog.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private final TipoJornadaRepository tipoJornadaRepository;
    private final BasePagoRepository basePagoRepository;
    private final DiaSemanaRepository diaSemanaRepository;
    private final BaseCotizacionRepository baseCotizacionRepository;
    private final TipoPeriodoEmpleadoRepository tipoPeriodoEmpleadoRepository;
    private final InconsistenciasRepository inconsistenciasRepository;
    private final JustificacionRepository justificacionRepository;

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
        cargarTipoPeriodoEmpresa();
        cargarTipoPrestacion();
        cargarZonaSalario();
        cargarTipoJornada();
        cargarBaseDePago();
        cargarTipoJornada();
        cargarDiaSemana();
        cargarBaseCotizacion();
        cargarTipoPeriodoEmpleado();
        cargarInconsistencias();
        cargarJustificacion();
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

    public void cargarTipoPeriodoEmpresa() {
        if(tipoPeriodoRepository.count() == 0) {
            for (TipoPeriodoEnum tipo : TipoPeriodoEnum.values()) {
                TipoPeriodo entidad = new TipoPeriodo();
                entidad.setPeriodo(tipo);
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

    private void cargarBaseDePago(){
        if(basePagoRepository.count() == 0){
            for(BasePagoEnum pago: BasePagoEnum.values()) {
                BaseDePago entidad = new BaseDePago();
                entidad.setBaseDePago(pago);
                basePagoRepository.save(entidad);
            }
        }
    }

    private void cargarBaseCotizacion(){
        if(baseCotizacionRepository.count()==0){
            for(BaseCotizacionEnum base: BaseCotizacionEnum.values()){
                BaseCotizacionEmpleado entidad = new BaseCotizacionEmpleado();
                entidad.setBaseCotizacion((base));
                baseCotizacionRepository.save(entidad);
            }
        }
    }

    private void cargarTipoPeriodoEmpleado(){
        if(tipoPeriodoEmpleadoRepository.count()==0){
            for (TipoPeriodoEmpleadoEnum tipoPeriodo: TipoPeriodoEmpleadoEnum.values()){
                TipoPeriodoEmpleado entidad = new TipoPeriodoEmpleado();
                entidad.setTipoPeriodoEmpleado(tipoPeriodo);
                tipoPeriodoEmpleadoRepository.save(entidad);
            }
        }
    }

    private void cargarTipoJornada(){
        if(tipoJornadaRepository.count() == 0){
            TipoJornada diurna = new TipoJornada();
            diurna.setTipoJornada(TipoJornadaEnum.DIURNA);
            diurna.setHoraInicio(LocalTime.of(6, 0));
            diurna.setHoraFin(LocalTime.of(20, 0));
            diurna.setDuracionMaxima(8.0);
            tipoJornadaRepository.save(diurna);

            TipoJornada nocturna = new TipoJornada();
            nocturna.setTipoJornada(TipoJornadaEnum.NOCTURNA);
            nocturna.setHoraInicio(LocalTime.of(20, 0));
            nocturna.setHoraFin(LocalTime.of(6, 0));
            nocturna.setDuracionMaxima(7.0);
            tipoJornadaRepository.save(nocturna);

            TipoJornada mixta = new TipoJornada();
            mixta.setTipoJornada(TipoJornadaEnum.MIXTA);
            mixta.setHoraInicio(null);
            mixta.setHoraFin(null);
            mixta.setDuracionMaxima(7.5);
            tipoJornadaRepository.save(mixta);
        }
    }

    private void cargarDiaSemana(){
        if(diaSemanaRepository.count()==0){
            for(DiaSemanaEnum dia: DiaSemanaEnum.values()){
                DiaSemana entidad= new DiaSemana();
                entidad.setDiaSemana(dia);
                diaSemanaRepository.save(entidad);
            }
        }
    }

    private void cargarInconsistencias(){
        if(inconsistenciasRepository.count()==0){
            for(InconsistenciasEnum inconsistencias: InconsistenciasEnum.values()){
                Inconsistencias entidad = new Inconsistencias();
                entidad.setInconsistencias(inconsistencias);
                inconsistenciasRepository.save(entidad);
            }
        }
    }

    private void cargarJustificacion(){
        if(justificacionRepository.count()==0){
            for(JustificacionEnum justificacion: JustificacionEnum.values()){
                Justificacion entidad = new Justificacion();
                entidad.setJustificacion(justificacion);
                justificacionRepository.save(entidad);
            }
        }
    }

}