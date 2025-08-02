package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.PrenominaRequest;
import com.miproyecto.trueque.dto.PrenominaResponse;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.Prenomina;
import com.miproyecto.trueque.model.Reporte;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.TramoISR;
import com.miproyecto.trueque.repository.DiasHorasRepository;
import com.miproyecto.trueque.repository.EmployeeRepository;
import com.miproyecto.trueque.repository.PrenominaRepository;
import com.miproyecto.trueque.repository.ReporteRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpleadoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class PrenominaService {
    private PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository;
    private EmployeeRepository employeeRepository;
    private DiasHorasRepository diasHorasRepository;
    private ReporteRepository reporteRepository;
    private PrenominaRepository prenominaRepository;

    public PrenominaService(PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository, EmployeeRepository employeeRepository, DiasHorasRepository diasHorasRepository, ReporteRepository reporteRepository, PrenominaRepository prenominaRepository) {
        this.periodosCreadosEmpleadoRepository = periodosCreadosEmpleadoRepository;
        this.employeeRepository = employeeRepository;
        this.diasHorasRepository = diasHorasRepository;
        this.reporteRepository = reporteRepository;
        this.prenominaRepository = prenominaRepository;
    }

    public void guardarPrenomina(Long empleadoId, Long tipoPeriodoId, PrenominaRequest request) {
        DatosPrenomina datos = obtenerDatosPrenomina(empleadoId, tipoPeriodoId);

        BigDecimal percepciones = Stream.of(
                        request.getBono(), request.getComisiones(), request.getGratificaciones(),
                        request.getAguinaldoProporcional(), request.getPrimaVacacional())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal ingresoTotal = datos.sueldoBase().add(percepciones);
        BigDecimal isr = calcularISR(ingresoTotal);
        BigDecimal deducciones = Stream.of(
                        isr,
                        request.getImss(), request.getInfonavit(), request.getOtrasDeducciones())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalNeto = ingresoTotal.subtract(deducciones);

        Prenomina prenomina = prenominaRepository
                .findByEmpleado_IdAndPeriodoPago_Id(empleadoId, datos.periodoActivo().getId())
                .orElse(new Prenomina());

        if (prenomina.getId() == null) {
            prenomina.setEmpleado(datos.empleado());
            prenomina.setPeriodoPago(datos.periodoActivo());
        }

        prenomina.setSueldoBase(datos.sueldoBase());
        prenomina.setHorasExtras(datos.pagoHorasExtras());

        prenomina.setBono(request.getBono());
        prenomina.setComisiones(request.getComisiones());
        prenomina.setGratificaciones(request.getGratificaciones());
        prenomina.setAguinaldoProporcional(request.getAguinaldoProporcional());
        prenomina.setPrimaVacacional(request.getPrimaVacacional());
        prenomina.setISR(isr);
        prenomina.setIMSS(request.getImss());
        prenomina.setINFONAVIT(request.getInfonavit());
        prenomina.setOtrasDeducciones(request.getOtrasDeducciones());
        prenomina.setTotalNeto(totalNeto.doubleValue());

        prenominaRepository.save(prenomina);
    }


    private DatosPrenomina obtenerDatosPrenomina(Long empleadoId, Long tipoPeriodoId) {
        PeriodoPago periodo = periodosCreadosEmpleadoRepository
                .findByTipoPeriodoEmpleado_IdAndEstadoTrue(tipoPeriodoId)
                .orElseThrow(() -> new RuntimeException("No hay periodo activo para este tipo de periodo"));

        Employee empleado = employeeRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        List<DiasHoras> diasHoras = diasHorasRepository.findByEmpleado_IdAndPeriodoActivo_Id(empleadoId, periodo.getId());
        BigDecimal totalHorasNormales = calcularTotalHorasNormales(diasHoras);

        Reporte reporte = reporteRepository
                .findByCodigoEmpleadoAndPeriodoPago_Id(empleado.getCodigoEmpleado(), periodo.getId())
                .orElse(null);

        BigDecimal horasExtras = reporte != null ? reporte.getHorasExtras() : BigDecimal.ZERO;
        int diasDescanso = reporte != null ? reporte.getDiasDescansoTrabajados() : 0;

        BigDecimal salarioDiario = empleado.getSalarioDiario();

        BigDecimal pagoNormales = calcularPagoHorasNormales(salarioDiario, totalHorasNormales);
        BigDecimal pagoDescanso = calcularPagoDiasDescanso(salarioDiario, diasDescanso);
        BigDecimal sueldoBase = pagoNormales.add(pagoDescanso);
        BigDecimal pagoExtras = calcularPagoHorasExtras(salarioDiario, horasExtras);

        return new DatosPrenomina(sueldoBase, pagoExtras, periodo, empleado);
    }

    private BigDecimal calcularTotalHorasNormales(List<DiasHoras> diasHoras) {
        BigDecimal total = BigDecimal.ZERO;
        for (DiasHoras dh : diasHoras) {
            BigDecimal horasExtras = dh.getHorasExtras() != null ? dh.getHorasExtras() : BigDecimal.ZERO;
            BigDecimal horasReales = dh.getHorasReales() != null ? dh.getHorasReales() : BigDecimal.ZERO;
            BigDecimal normales = horasReales.subtract(horasExtras);
            if (normales.compareTo(BigDecimal.ZERO) < 0) {
                normales = BigDecimal.ZERO;
            }
            total = total.add(normales);
        }
        return total;
    }

    private BigDecimal calcularPagoHorasNormales(BigDecimal salarioDiario, BigDecimal totalHorasNormales) {
        return salarioDiario.multiply(totalHorasNormales).divide(BigDecimal.valueOf(8), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularPagoDiasDescanso(BigDecimal salarioDiario, int diasDescanso) {
        return salarioDiario.multiply(BigDecimal.valueOf(diasDescanso * 2));
    }

    private BigDecimal calcularPagoHorasExtras(BigDecimal salarioDiario, BigDecimal horasExtras) {
        return salarioDiario.multiply(horasExtras).multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(8), 2, RoundingMode.HALF_UP);
    }

    private record DatosPrenomina(
            BigDecimal sueldoBase,
            BigDecimal pagoHorasExtras,
            PeriodoPago periodoActivo,
            Employee empleado
    ) {
    }

    public BigDecimal calcularISR(BigDecimal ingresoTotal) {
        List<TramoISR> tablaISR = Arrays.asList(
                new TramoISR("0.01", "496.07", "0.00", "1.92"),
                new TramoISR("496.08", "4210.41", "9.52", "6.40"),
                new TramoISR("4210.42", "7399.42", "247.24", "10.88"),
                new TramoISR("7399.43", "8601.50", "594.21", "16.00"),
                new TramoISR("8601.51", "10298.35", "786.54", "17.92"),
                new TramoISR("10298.36", "20770.29", "1090.61", "21.36"),
                new TramoISR("20770.30", "32736.83", "3327.42", "23.52"),
                new TramoISR("32736.84", "62500.00", "6141.95", "30.00"),
                new TramoISR("62500.01", "83333.33", "15070.90", "32.00"),
                new TramoISR("83333.34", "250000.00", "21737.57", "34.00"),
                new TramoISR("250000.01", null, "78404.23", "35.00")
        );

        for (TramoISR tramo : tablaISR) {
            BigDecimal li = tramo.getLimiteInferior();
            BigDecimal ls = tramo.getLimiteSuperior();

            if (ingresoTotal.compareTo(li) >= 0 && (ls == null || ingresoTotal.compareTo(ls) <= 0)) {
                BigDecimal excedente = ingresoTotal.subtract(li);
                return tramo.getCuotaFija()
                        .add(excedente.multiply(tramo.getPorcentajeSobreExcedente())
                                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
                        .setScale(2, RoundingMode.HALF_UP);
            }
        }

        return BigDecimal.ZERO;
    }

    public List<PrenominaResponse> generarPrenominaPorTipoPeriodo(Long tipoPeriodoEmpleadoId) {
        PeriodoPago periodo = periodosCreadosEmpleadoRepository.findByTipoPeriodoEmpleado_IdAndEstadoTrue(tipoPeriodoEmpleadoId)
                .orElseThrow(() -> new RuntimeException("No hay periodo activo para este tipo."));

        List<Prenomina> registros = prenominaRepository.findByPeriodoPago_Id(periodo.getId());

        return registros.stream().map(p -> new PrenominaResponse(
                p.getSueldoBase(),
                p.getHorasExtras(),
                p.getBono(),
                p.getComisiones(),
                p.getGratificaciones(),
                p.getAguinaldoProporcional(),
                p.getPrimaVacacional(),
                p.getISR(),
                p.getIMSS(),
                p.getINFONAVIT(),
                p.getOtrasDeducciones(),
                p.getTotalNeto()
        )).toList();
    }
}
