package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.CatalogsResponse;
import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.repository.catalog.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CatalogService(IMSSEmployeeRepository imssEmployeeRepository, SBCEmpleadoRepository sbcEmpleadoRepository,
                          EntidadFederativaRepository entidadFederativaRepository, EstadoCivilRepository estadoCivilRepository,
                          GeneroRepository generoRepository, MetodoPagoRepository metodoPagoRepository, RegimenEmpleadoRepository regimenEmpleadoRepository,
                          RegimenEmpresaRepository regimenEmpresaRepository, SindicatoEmpleadoRepository sindicatoEmpleadoRepository, TipoCodigoEmpleadoRepository tipoCodigoEmpleadoRepository,
                          TipoContratoEmpleadoRepository tipoContratoEmpleadoRepository, TipoPeriodoRepository tipoPeriodoRepository,
                          TipoPrestacionRepository tipoPrestacionRepository, ZonaSalarioRepository zonaSalarioRepository){
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
    }

    public CatalogsResponse getAllCatalogs(){
        List<GenericCatalogResponse> imssEmpleado = imssEmployeeRepository.getAllAvisoIMSS();
        List<GenericCatalogResponse> sbcEmpleado = sbcEmpleadoRepository.getAllSBCEmpl();
        List<GenericCatalogResponse> entidadFederativa = entidadFederativaRepository.getAllEntidad();
        List<GenericCatalogResponse> estadoCivil = estadoCivilRepository.getAllEstadoCivil();
        List<GenericCatalogResponse> genero = generoRepository.getAllGenres();
        List<GenericCatalogResponse> metodoPagoEmpleado = metodoPagoRepository.getAllMetodoPago();
        List<GenericCatalogResponse> regimenEmpleado = regimenEmpleadoRepository.getAllRegimenEmpleado();
        List<GenericCatalogResponse> regimenEmpresa = regimenEmpresaRepository.getAllRegimenEmpresa();
        List<GenericCatalogResponse> sindicato = sindicatoEmpleadoRepository.getAllSindicato();
        List<GenericCatalogResponse> tipoContrato = tipoContratoEmpleadoRepository.getAllTipoContrato();
        List<GenericCatalogResponse> tipoCodigo = tipoCodigoEmpleadoRepository.getAllTipoCodigo();
        List<GenericCatalogResponse> tipoPeriodo = tipoPeriodoRepository.getAllTipoPeriodo();
        List<GenericCatalogResponse> tipoPrestacion = tipoPrestacionRepository.getAllTipoPrestacion();
        List<GenericCatalogResponse> zonaSalario = zonaSalarioRepository.getAllZonaSalario();

        return new CatalogsResponse(imssEmpleado, sbcEmpleado, entidadFederativa, estadoCivil, genero, metodoPagoEmpleado, regimenEmpleado, regimenEmpresa, sindicato, tipoCodigo, tipoContrato, tipoPeriodo, tipoPrestacion, zonaSalario);

    }

    public List<GenericCatalogResponse> obtenerGeneros(){
        return generoRepository.getAllGenres();
    }
}
