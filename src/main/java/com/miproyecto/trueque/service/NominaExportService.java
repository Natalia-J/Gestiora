package com.miproyecto.trueque.service;

import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.Prenomina;
import com.miproyecto.trueque.repository.EmployeeRepository;
import com.miproyecto.trueque.repository.PrenominaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NominaExportService {
    private final PrenominaRepository prenominaRepository;
    private final EmployeeRepository employeeRepository;
    private final PdfService pdfService;

    public byte[] exportPdf(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();


        Prenomina employeeFound = prenominaRepository.findByEmpleadoId(employeeId).orElseThrow();

        return pdfService.generateNominaPdf(employeeFound);
    }
    public byte[] exportPdfMultiplesZip(List<Long> employeeIds) {
        List<Prenomina> prenominas = employeeIds.stream()
                .map(id -> prenominaRepository.findByEmpleadoId(id)
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + id)))
                .toList();

        return pdfService.generateNominaZipMultiples(prenominas);
    }

    public byte[] exportPdfMultiples(List<Long> employeeIds) {
        List<Prenomina> prenominas = employeeIds.stream()
                .map(id -> prenominaRepository.findByEmpleadoId(id)
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + id)))
                .toList();

        // Aquí debes implementar la lógica para combinar varios prenominas en un solo PDF
        return pdfService.generateNominaPdfMultiples(prenominas);
    }



}
