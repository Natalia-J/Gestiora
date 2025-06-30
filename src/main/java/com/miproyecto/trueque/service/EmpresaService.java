package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.DireccionRequest;
import com.miproyecto.trueque.dto.EmpresaRequest;
import com.miproyecto.trueque.exception.EmpresaExistenteException;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.Direccion;
import com.miproyecto.trueque.repository.EmpresaRepository;
import com.miproyecto.trueque.repository.catalog.RegimenEmpresaRepository;
import com.miproyecto.trueque.repository.catalog.TipoCodigoEmpleadoRepository;
import com.miproyecto.trueque.repository.catalog.ZonaSalarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private EmpresaRepository empresaRepository;
    private ZonaSalarioRepository zonaSalarioRepository;
    private TipoCodigoEmpleadoRepository tipoCodigoEmpleadoRepository;
    private RegimenEmpresaRepository regimenEmpresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository, ZonaSalarioRepository zonaSalarioRepository, TipoCodigoEmpleadoRepository tipoCodigoEmpleadoRepository, RegimenEmpresaRepository regimenEmpresaRepository){
        this.empresaRepository = empresaRepository;
        this.zonaSalarioRepository = zonaSalarioRepository;
        this.tipoCodigoEmpleadoRepository = tipoCodigoEmpleadoRepository;
        this.regimenEmpresaRepository = regimenEmpresaRepository;
    }

    public Company crearEmpresa(EmpresaRequest dto){
        if(empresaRepository.existsByRfc(dto.getRfc())){
            throw new EmpresaExistenteException("Ya existe una empresa con el RFC: " + dto.getRfc());
        }

        Company company = mapDtoToEntity(dto);

        return empresaRepository.save(company);
    }

    private Company mapDtoToEntity(EmpresaRequest dto) {
        Company company = new Company();

        company.setNombre(dto.getNombre());
        company.setMascarilla(dto.getMascarilla());
        company.setVigencia(dto.getVigencia());
        company.setFecha_inicio(dto.getFecha_inicio());
        company.setFactor(dto.getFactor());
        company.setTelefono1(dto.getTelefono1());
        company.setTelefono2(dto.getTelefono2());
        company.setPatronalIMSS(dto.getPatronalIMSS());
        company.setRfc(dto.getRfc());
        company.setInfonavit(dto.getInfonavit());
        company.setNombreRepresentante(dto.getNombreRepresentante());
        company.setApellidoPaRepresentante(dto.getApellidoPaRepresentante());
        company.setApellidoMaRepresentante(dto.getApellidoMaRepresentante());

        company.setZonaSalario(
                zonaSalarioRepository.findById(dto.getZonaSalarioId())
                        .orElseThrow(() -> new RuntimeException("Zona salarial no encontrada"))
        );

        company.setTipoCodigoEmpleado(
                tipoCodigoEmpleadoRepository.findById(dto.getTipoCodigoEmpleadoId())
                        .orElseThrow(() -> new RuntimeException("Tipo código empleado no encontrado"))
        );

        company.setRegimenFiscalCompany(
                regimenEmpresaRepository.findById(dto.getRegimenFiscalCompanyId())
                        .orElseThrow(() -> new RuntimeException("Régimen fiscal no encontrado"))
        );

        company.setDireccionCompany(DireccionRequestToDireccion(dto.getDireccionCompany()));

        return company;
    }

    private Direccion DireccionRequestToDireccion(DireccionRequest dto) {
        Direccion direccion = new Direccion();

        direccion.setCalleEmpresa(dto.getCalle());
        direccion.setNumInterno(dto.getNumInterno());
        direccion.setNumExterno(dto.getNumExterno());
        direccion.setColoniaEmpresa(dto.getColonia());
        direccion.setCodigoPostalEmpresa(dto.getCodigoPostal());
        direccion.setLocalidadEmpresa(dto.getLocalidad());

        return direccion;
    }

    public List<Company> obtenerEmpresa(){
        return empresaRepository.findAll();
    }

    public Optional<Company> obtenerEmpresaPorID(Long id){
        return empresaRepository.findById(id);
    }
}

/*public class EmpresaService {
    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public Company crearEmpresa(Company company){
        if(empresaRepository.existsByRfc(company.getRfc())){
            throw new EmpresaExistenteException("Ya existe una empresa con el RFC:" + company.getRfc());
        }
        return empresaRepository.save(company);
    }

    public List<Company> obtenerEmpresa(){
        return empresaRepository.findAll();
    }

    public Optional<Company> obtenerEmpresaPorID(Long id){
        return empresaRepository.findById(id);
    }
}*/
