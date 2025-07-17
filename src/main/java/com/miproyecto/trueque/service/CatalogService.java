package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.CatalogsResponse;
import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.repository.catalog.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.ElementCSSInlineStyle;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    private IMSSEmployeeRepository imssEmployeeRepository;
    private SBCEmpleadoRepository sbcEmpleadoRepository;
    private EntidadFederativaRepository entidadFederativaRepository;
    private EstadoCivilRepository estadoCivilRepository;
    private GeneroRepository generoRepository;
    private MetodoPagoRepository metodoPagoRepository;
    private RegimenEmpleadoRepository regimenEmpleadoRepository;
    private RegimenEmpresaRepository regimenEmpresaRepository;
    private SindicatoEmpleadoRepository sindicatoEmpleadoRepository;
    private TipoCodigoEmpleadoRepository tipoCodigoEmpleadoRepository;
    private TipoContratoEmpleadoRepository tipoContratoEmpleadoRepository;
    private TipoPeriodoRepository tipoPeriodoRepository;
    private TipoPrestacionRepository tipoPrestacionRepository;
    private ZonaSalarioRepository zonaSalarioRepository;
    private TipoJornadaRepository tipoJornadaRepository;
    private BasePagoRepository basePagoRepository;
    private DiaSemanaRepository diaSemanaRepository;

    public CatalogService(IMSSEmployeeRepository imssEmployeeRepository, SBCEmpleadoRepository sbcEmpleadoRepository,
                          EntidadFederativaRepository entidadFederativaRepository, EstadoCivilRepository estadoCivilRepository,
                          GeneroRepository generoRepository, MetodoPagoRepository metodoPagoRepository, RegimenEmpleadoRepository regimenEmpleadoRepository,
                          RegimenEmpresaRepository regimenEmpresaRepository, SindicatoEmpleadoRepository sindicatoEmpleadoRepository, TipoCodigoEmpleadoRepository tipoCodigoEmpleadoRepository,
                          TipoContratoEmpleadoRepository tipoContratoEmpleadoRepository, TipoPeriodoRepository tipoPeriodoRepository,
                          TipoPrestacionRepository tipoPrestacionRepository, ZonaSalarioRepository zonaSalarioRepository, TipoJornadaRepository tipoJornadaRepository,
                          BasePagoRepository basePagoRepository, DiaSemanaRepository diaSemanaRepository){
        this.imssEmployeeRepository = imssEmployeeRepository;
        this.sbcEmpleadoRepository = sbcEmpleadoRepository;
        this.entidadFederativaRepository = entidadFederativaRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.generoRepository = generoRepository;
        this.metodoPagoRepository = metodoPagoRepository;
        this.regimenEmpleadoRepository = regimenEmpleadoRepository;
        this.regimenEmpresaRepository = regimenEmpresaRepository;
        this.sindicatoEmpleadoRepository = sindicatoEmpleadoRepository;
        this.tipoCodigoEmpleadoRepository = tipoCodigoEmpleadoRepository;
        this.tipoContratoEmpleadoRepository = tipoContratoEmpleadoRepository;
        this.tipoPeriodoRepository = tipoPeriodoRepository;
        this.tipoPrestacionRepository = tipoPrestacionRepository;
        this. zonaSalarioRepository = zonaSalarioRepository;
        this.tipoJornadaRepository = tipoJornadaRepository;
        this.basePagoRepository = basePagoRepository;
        this.diaSemanaRepository = diaSemanaRepository;
    }

    public CatalogsResponse getAllCatalogs(){
        List<GenericCatalogResponse> imssEmpleado = imssEmployeeRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getAvisoImssEmpleado().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> sbcEmpleado = sbcEmpleadoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getSbc().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> entidadFederativa = entidadFederativaRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getEntidadFederativa().getDescipcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> estadoCivil = estadoCivilRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getEstadoCivilEmpleado().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> genero = generoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getGenero().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> metodoPagoEmpleado = metodoPagoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getMetodoPago().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> regimenEmpleado = regimenEmpleadoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getRegimenFiscalEmployee().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> regimenEmpresa = regimenEmpresaRepository.findAll()
                .stream()
                .map(r -> new GenericCatalogResponse(
                        r.getId(),
                        r.getRegimenEmpresa().getDescripcion()
                ))
                .collect(Collectors.toList());
        List<GenericCatalogResponse> sindicato = sindicatoEmpleadoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getSindicato().getDescription()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> tipoContrato = tipoContratoEmpleadoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getContrato().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> tipoCodigo = tipoCodigoEmpleadoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getCodigo().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> tipoPeriodo = tipoPeriodoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getPeriodo().getDescription()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> tipoPrestacion = tipoPrestacionRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getTipoPrestacionEmpleado().getDescription()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> zonaSalario = zonaSalarioRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getZonaSalario().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> tipoJornada = tipoJornadaRepository.findAll()
                .stream().map(r -> new GenericCatalogResponse(
                        r.getId(),
                        r.getTipoJornada().getDescripcion()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> baseDePago = basePagoRepository.findAll()
                .stream().map(r-> new GenericCatalogResponse(
                        r.getId(),
                        r.getBaseDePago().getDesciption()
                )).collect(Collectors.toList());
        List<GenericCatalogResponse> diaSemana = diaSemanaRepository.findAll()
                .stream().map(r->new GenericCatalogResponse(
                        r.getId(),
                        r.getDiaSemana().getDescripcion()
                )).collect(Collectors.toList());

        return new CatalogsResponse(imssEmpleado, sbcEmpleado, entidadFederativa, estadoCivil, genero, metodoPagoEmpleado, regimenEmpleado, regimenEmpresa, sindicato, tipoCodigo, tipoContrato, tipoPeriodo, tipoPrestacion, zonaSalario, tipoJornada, baseDePago, diaSemana);

    }

    public List<GenericCatalogResponse> obtenerGeneros(){
        return generoRepository.getAllGenres();
    }
}
