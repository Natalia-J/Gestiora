package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.DireccionRequest;
import com.miproyecto.trueque.dto.EmpleadoResponse;
import com.miproyecto.trueque.dto.EmployeeObtenerResponse;
import com.miproyecto.trueque.dto.EmployeeRequest;
import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.model.*;
import com.miproyecto.trueque.model.catalogs.Direccion;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import com.miproyecto.trueque.model.enums.CodigEmpleEnum;
import com.miproyecto.trueque.repository.*;
import com.miproyecto.trueque.repository.catalog.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmpresaRepository empresaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final PuestoRepository puestoRepository;
    private final SindicatoEmpleadoRepository sindicatoRepository;
    private final TipoPrestacionRepository tipoPrestacionRepository;
    private final BasePagoRepository basePagoRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final TurnoRepository turnoTrabajoRepository;
    private final ZonaSalarioRepository zonaSalarioRepository;
    private final RegimenEmpleadoRepository tipoRegimenRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final GeneroRepository generoRepository;
    private final EntidadFederativaRepository entidadFederativaRepository;
    private final DireccionRepository direccionRepository;
    private final TipoContratoEmpleadoRepository tipoContratoRepository;
    private final TipoPeriodoEmpleadoRepository tipoPeriodoEmpleadoRepository;
    private final BaseCotizacionRepository baseCotizacionRepository;
    private final DiasHorasService diasHorasService;
    private final PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getByCodigoEmpleado(String codigo) {
        return employeeRepository.findByCodigoEmpleado(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con código: " + codigo));
    }

    @Transactional
    public Employee createEmployee(EmployeeRequest request) {
        Long empresaId = EmpresaContextHolder.getEmpresaId();

        Company empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada en contexto"));

        validarCodigoEmpleado(
                request.getCodigoEmpleado(),
                empresa.getMascarilla(),
                empresa.getTipoCodigoEmpleado().getCodigo()
        );

        validarCorreo(request.getCorreo());
        validarCurp(request.getCurp());
        validarRFC(request.getRfc());

        Employee empleado = new Employee();

        empleado.setEmpresa(empresa);
        empleado.setCodigoEmpleado(request.getCodigoEmpleado());
        empleado.setNombreEmpleado(request.getNombre());
        empleado.setApellidoPaternoEmpleado(request.getApellidoPaterno());
        empleado.setApellidoMaternoEmpleado(request.getApellidoMaterno());
        empleado.setFechaAlta(request.getFechaAlta());
        empleado.setSalarioDiario(request.getSalarioDiario());
        empleado.setAforeEmpleado(request.getAfore());
        empleado.setCorreoEmpleado(request.getCorreo());
        empleado.setNumeroEmpleado(request.getNumTelefono());
        empleado.setNumSeguridadSocial(request.getNumSeguridadSocial());
        empleado.setRegistroPatronalIMSS(request.getRegistroPatronalImss());
        empleado.setFechaNacimientoEmpleado(request.getFechaNacimiento());
        empleado.setCiudadNacimientoEmpleado(request.getCiudad());
        empleado.setCurpEmpleado(request.getCurp());
        empleado.setRfcEmpleado(request.getRfc());

        empleado.setTipoContratoEmpleado(tipoContratoRepository.findById(request.getTipoContrato()).orElse(null));
        empleado.setTipoPeriodo(tipoPeriodoEmpleadoRepository.findById(request.getTipoPeriodo()).orElse(null));
        empleado.setBaseCotizacion(baseCotizacionRepository.findById(request.getBaseCotizacion()).orElse(null));
        empleado.setDepartamentoEmple(departamentoRepository.findById(request.getDepartamento()).orElse(null));
        empleado.setPuesto(puestoRepository.findById(request.getPuesto()).orElse(null));
        empleado.setSindicatoEmpleado(sindicatoRepository.findById(request.getSindicato()).orElse(null));
        empleado.setTipoPrestacionEmpleado(tipoPrestacionRepository.findById(request.getTipoPrestacion()).orElse(null));
        empleado.setMetodoPagoEmpleado(metodoPagoRepository.findById(request.getMetodoPago()).orElse(null));
        empleado.setTurno(turnoTrabajoRepository.findById(request.getTurnoTrabajo()).orElse(null));
        empleado.setZonaSalario(zonaSalarioRepository.findById(request.getZonaSalario()).orElse(null));
        empleado.setRegimenEmployee(tipoRegimenRepository.findById(request.getTipoRegimen()).orElse(null));
        empleado.setEstadoCivil(estadoCivilRepository.findById(request.getEstadoCivil()).orElse(null));
        empleado.setGenero(generoRepository.findById(request.getGenero()).orElse(null));
        empleado.setEntidadFederativa(entidadFederativaRepository.findById(request.getEntidadFederativa()).orElse(null));
        empleado.setBaseDePago(basePagoRepository.findById(request.getBaseDePago()).orElse(null));

        Direccion direccion = new Direccion();
        direccion.setCalleEmpresa(request.getDireccion().getCalle());
        direccion.setNumExterno(request.getDireccion().getNumExterno());
        direccion.setNumInterno(request.getDireccion().getNumInterno());
        direccion.setColoniaEmpresa(request.getDireccion().getColonia());
        direccion.setCodigoPostalEmpresa(request.getDireccion().getCodigoPostal());
        direccion.setLocalidadEmpresa(request.getDireccion().getLocalidad());
        direccionRepository.save(direccion);

        empleado.setDireccionEmployee(direccion);

        Employee empleadoGuardado = employeeRepository.save(empleado);

        if (request.getTipoPeriodo() != null) {
            TipoPeriodoEmpleado tipoPeriodoNuevo = tipoPeriodoEmpleadoRepository
                    .findById(request.getTipoPeriodo())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de periodo no encontrado"));

            empleadoGuardado.setTipoPeriodo(tipoPeriodoNuevo);
            employeeRepository.save(empleadoGuardado);
        }

        PeriodoPago periodoPago = periodosCreadosEmpleadoRepository
                .findFirstByEmpresaIdAndEstadoTrueAndTipoPeriodoEmpleadoIdOrderByFechaInicioDesc(
                        empleado.getEmpresa().getId(),
                        empleado.getTipoPeriodo().getId()
                )
                .orElseThrow(() -> new RuntimeException("No se encontró un periodo activo para el tipo de periodo del empleado"));

        diasHorasService.generarDiasParaPeriodoEmpleado(empleadoGuardado.getId(), periodoPago.getId());

        return empleadoGuardado;
    }


    private void validarCodigoEmpleado(String codigo, String mascarilla, CodigEmpleEnum tipoCodigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código de empleado no puede estar vacío.");
        }

        int longitudEsperada = (int) mascarilla.chars().filter(ch -> ch == 'X').count();
        if (codigo.length() != longitudEsperada) {
            throw new IllegalArgumentException("El código debe tener " + longitudEsperada + " caracteres según la mascarilla.");
        }

        switch (tipoCodigo) {
            case NUMERICO:
                if (!codigo.matches("\\d+")) {
                    throw new IllegalArgumentException("El código debe ser numérico.");
                }
                break;
            case ALFANUMERICO:
                if (!codigo.matches("[A-Za-z0-9]+")) {
                    throw new IllegalArgumentException("El código debe ser alfanumérico.");
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo de código de empleado no soportado.");
        }

        if (employeeRepository.existsByCodigoEmpleado(codigo)) {
            throw new IllegalArgumentException("Ya existe un empleado con ese código.");
        }
    }

    private void validarCorreo(String correo) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
        if (!Pattern.matches(regex, correo)) {
            throw new IllegalArgumentException("Correo inválido.");
        }
    }

    private void validarCurp(String curp) {
        String regex = "^[A-Z]{4}\\d{6}[HM][A-Z]{5}\\d{2}$";
        if (!Pattern.matches(regex, curp)) {
            throw new IllegalArgumentException("CURP inválido.");
        }
    }

    private void validarRFC(String rfc) {
        String regex = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$";
        if (!Pattern.matches(regex, rfc)) {
            throw new IllegalArgumentException("RFC inválido.");
        }
    }
    public boolean existenEmpleadosEnDepartamento(Long departamentoId) {
        return employeeRepository.existsByDepartamentoEmple_Id(departamentoId);
    }


    @Transactional
    public void moverEmpleadoADepartamento(Long empleadoId, Long nuevoDepartamentoId) {
        Employee empleado = employeeRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Departamento nuevoDepartamento = departamentoRepository.findById(nuevoDepartamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Departamento destino no encontrado"));

        empleado.setDepartamentoEmple(nuevoDepartamento);
        employeeRepository.save(empleado);
    }

    public List<EmpleadoResponse> obtenerEmpleadosPorTipoPeriodo(Long tipoPeriodo) {
        List<Employee> empleados = employeeRepository.findByTipoPeriodoId(tipoPeriodo);
        return empleados.stream()
                .map(e -> new EmpleadoResponse(e.getId(), e.getNombreEmpleado(), e.getCodigoEmpleado()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeRequest request) {
        Employee empleado = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        empleado.setCodigoEmpleado(request.getCodigoEmpleado());
        empleado.setNombreEmpleado(request.getNombre());
        empleado.setApellidoPaternoEmpleado(request.getApellidoPaterno());
        empleado.setApellidoMaternoEmpleado(request.getApellidoMaterno());
        empleado.setFechaAlta(request.getFechaAlta());
        empleado.setSalarioDiario(request.getSalarioDiario());
        empleado.setAforeEmpleado(request.getAfore());
        empleado.setCorreoEmpleado(request.getCorreo());
        empleado.setNumeroEmpleado(request.getNumTelefono());
        empleado.setNumSeguridadSocial(request.getNumSeguridadSocial());
        empleado.setRegistroPatronalIMSS(request.getRegistroPatronalImss());
        empleado.setFechaNacimientoEmpleado(request.getFechaNacimiento());
        empleado.setCiudadNacimientoEmpleado(request.getCiudad());
        empleado.setCurpEmpleado(request.getCurp());
        empleado.setRfcEmpleado(request.getRfc());

        empleado.setTipoContratoEmpleado(tipoContratoRepository.findById(request.getTipoContrato()).orElse(null));
        empleado.setTipoPeriodo(tipoPeriodoEmpleadoRepository.findById(request.getTipoPeriodo()).orElse(null));
        empleado.setBaseCotizacion(baseCotizacionRepository.findById(request.getBaseCotizacion()).orElse(null));
        empleado.setDepartamentoEmple(departamentoRepository.findById(request.getDepartamento()).orElse(null));
        empleado.setPuesto(puestoRepository.findById(request.getPuesto()).orElse(null));
        empleado.setSindicatoEmpleado(sindicatoRepository.findById(request.getSindicato()).orElse(null));
        empleado.setTipoPrestacionEmpleado(tipoPrestacionRepository.findById(request.getTipoPrestacion()).orElse(null));
        empleado.setMetodoPagoEmpleado(metodoPagoRepository.findById(request.getMetodoPago()).orElse(null));
        empleado.setTurno(turnoTrabajoRepository.findById(request.getTurnoTrabajo()).orElse(null));
        empleado.setZonaSalario(zonaSalarioRepository.findById(request.getZonaSalario()).orElse(null));
        empleado.setRegimenEmployee(tipoRegimenRepository.findById(request.getTipoRegimen()).orElse(null));
        empleado.setEstadoCivil(estadoCivilRepository.findById(request.getEstadoCivil()).orElse(null));
        empleado.setGenero(generoRepository.findById(request.getGenero()).orElse(null));
        empleado.setEntidadFederativa(entidadFederativaRepository.findById(request.getEntidadFederativa()).orElse(null));
        empleado.setBaseDePago(basePagoRepository.findById(request.getBaseDePago()).orElse(null));

        Direccion direccion = empleado.getDireccionEmployee();
        if (direccion == null) {
            direccion = new Direccion();
        }
        direccion.setCalleEmpresa(request.getDireccion().getCalle());
        direccion.setNumExterno(request.getDireccion().getNumExterno());
        direccion.setNumInterno(request.getDireccion().getNumInterno());
        direccion.setColoniaEmpresa(request.getDireccion().getColonia());
        direccion.setCodigoPostalEmpresa(request.getDireccion().getCodigoPostal());
        direccion.setLocalidadEmpresa(request.getDireccion().getLocalidad());
        direccionRepository.save(direccion);

        empleado.setDireccionEmployee(direccion);

        return employeeRepository.save(empleado);
    }

    @Transactional
    public void deleteEmployee(Long empleadoId) {
        Employee empleado = employeeRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + empleadoId));
        employeeRepository.delete(empleado);
    }

    public EmployeeObtenerResponse obtenerEmpleadoPorId(Long empleadoId) {
        Employee empleado = employeeRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + empleadoId));

        DireccionRequest direccionRequest = null;
        if (empleado.getDireccionEmployee() != null) {
            direccionRequest = new DireccionRequest();
            direccionRequest.setCalle(empleado.getDireccionEmployee().getCalleEmpresa());
            direccionRequest.setNumExterno(empleado.getDireccionEmployee().getNumExterno());
            direccionRequest.setNumInterno(empleado.getDireccionEmployee().getNumInterno());
            direccionRequest.setColonia(empleado.getDireccionEmployee().getColoniaEmpresa());
            direccionRequest.setCodigoPostal(empleado.getDireccionEmployee().getCodigoPostalEmpresa());
            direccionRequest.setLocalidad(empleado.getDireccionEmployee().getLocalidadEmpresa());
        }

        return new EmployeeObtenerResponse(
                empleado.getCodigoEmpleado(),
                empleado.getNombreEmpleado(),
                empleado.getApellidoPaternoEmpleado(),
                empleado.getApellidoMaternoEmpleado(),
                empleado.getFechaAlta(),
                empleado.getTipoContratoEmpleado() != null ? empleado.getTipoContratoEmpleado().getId() : null,
                empleado.getDepartamentoEmple() != null ? empleado.getDepartamentoEmple().getId() : null,
                empleado.getTipoPeriodo() != null ? empleado.getTipoPeriodo().getId() : null,
                empleado.getSalarioDiario(),
                empleado.getBaseCotizacion() != null ? empleado.getBaseCotizacion().getId() : null,

                empleado.getDepartamentoEmple() != null ? empleado.getDepartamentoEmple().getId() : null,
                empleado.getPuesto() != null ? empleado.getPuesto().getId() : null,
                empleado.getSindicatoEmpleado() != null ? empleado.getSindicatoEmpleado().getId() : null,
                empleado.getTipoPrestacionEmpleado() != null ? empleado.getTipoPrestacionEmpleado().getId() : null,
                empleado.getBaseDePago() != null ? empleado.getBaseDePago().getId() : null,
                empleado.getMetodoPagoEmpleado() != null ? empleado.getMetodoPagoEmpleado().getId() : null,
                empleado.getTurno() != null ? empleado.getTurno().getId() : null,
                empleado.getZonaSalario() != null ? empleado.getZonaSalario().getId() : null,
                empleado.getRegimenEmployee() != null ? empleado.getRegimenEmployee().getId() : null,
                empleado.getAforeEmpleado(),
                empleado.getCorreoEmpleado(),
                empleado.getNumeroEmpleado(),
                empleado.getNumSeguridadSocial(),
                empleado.getRegistroPatronalIMSS(),
                empleado.getEstadoCivil() != null ? empleado.getEstadoCivil().getId() : null,
                empleado.getGenero() != null ? empleado.getGenero().getId() : null,
                empleado.getFechaNacimientoEmpleado(),
                empleado.getEntidadFederativa() != null ? empleado.getEntidadFederativa().getId() : null,
                empleado.getCiudadNacimientoEmpleado(),
                empleado.getCurpEmpleado(),
                empleado.getRfcEmpleado(),
                direccionRequest
        );
    }




}
