package com.miproyecto.trueque.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.miproyecto.trueque.model.Prenomina;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
    private static final Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
    private static final Font SECTION_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY);
    private static final Font CONTENT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
    private static final Font BOLD_CONTENT_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);

    public byte[] generateNominaPdf(Prenomina prenomina) {
        try {
            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();

            addCompanyHeader(document);

            addEmployeeInfo(document, prenomina);

            // Tabla de percepciones
            addPercepcionesTable(document, prenomina);

            // Tabla de deducciones
            addDeduccionesTable(document, prenomina);

            // Total neto
            addTotalNeto(document, prenomina);

            // Footer
            addFooter(document);

            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de nómina: " + e.getMessage(), e);
        }
    }

    private void addCompanyHeader(Document document) throws DocumentException {
        Paragraph title = new Paragraph("RECIBO DE NÓMINA", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable companyTable = new PdfPTable(2);
        companyTable.setWidthPercentage(100);
        companyTable.setSpacingAfter(20);

        PdfPCell leftCell = new PdfPCell();
        leftCell.setBorder(Rectangle.NO_BORDER);
        leftCell.addElement(new Paragraph("MI EMPRESA S.A. DE C.V.", BOLD_CONTENT_FONT));
        leftCell.addElement(new Paragraph("RFC: MEE123456789", CONTENT_FONT));
        leftCell.addElement(new Paragraph("Dirección: Calle Principal #123", CONTENT_FONT));
        leftCell.addElement(new Paragraph("Ciudad, Estado, CP 12345", CONTENT_FONT));

        PdfPCell rightCell = new PdfPCell();
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        rightCell.addElement(new Paragraph("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), CONTENT_FONT));

        companyTable.addCell(leftCell);
        companyTable.addCell(rightCell);
        document.add(companyTable);

        document.add(new Paragraph(" "));
    }

    private void addEmployeeInfo(Document document, Prenomina prenomina) throws DocumentException {
        PdfPTable employeeTable = new PdfPTable(2);
        employeeTable.setWidthPercentage(100);
        employeeTable.setSpacingAfter(15);

        PdfPCell headerCell = new PdfPCell(new Phrase("INFORMACIÓN DEL EMPLEADO", HEADER_FONT));
        headerCell.setColspan(2);
        headerCell.setBackgroundColor(new BaseColor(52, 73, 94));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(8);
        employeeTable.addCell(headerCell);

        // Datos del empleado
        addEmployeeDataRow(employeeTable, "Nombre:",
                prenomina.getEmpleado().getNombreEmpleado() + " " + prenomina.getEmpleado().getApellidoPaternoEmpleado() + " " + prenomina.getEmpleado().getApellidoMaternoEmpleado());
        addEmployeeDataRow(employeeTable, "No. Empleado:",
                prenomina.getEmpleado().getNumeroEmpleado());
        addEmployeeDataRow(employeeTable, "Puesto:",
                prenomina.getEmpleado().getPuesto() != null ? prenomina.getEmpleado().getPuesto().getNombre() : "N/A");
        addEmployeeDataRow(employeeTable, "Período de pago:",
                prenomina.getPeriodoPago().getFechaInicio() + " " + prenomina.getPeriodoPago().getFechaFin());

        document.add(employeeTable);
    }

    private void addEmployeeDataRow(PdfPTable table, String label, String value) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, BOLD_CONTENT_FONT));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(5);
        labelCell.setBackgroundColor(new BaseColor(236, 240, 241));

        PdfPCell valueCell = new PdfPCell(new Phrase(value, CONTENT_FONT));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(5);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    private void addPercepcionesTable(Document document, Prenomina prenomina) throws DocumentException {
        document.add(new Paragraph("PERCEPCIONES", SECTION_FONT));

        PdfPTable percepcionesTable = new PdfPTable(2);
        percepcionesTable.setWidthPercentage(100);
        percepcionesTable.setSpacingAfter(15);
        percepcionesTable.setWidths(new float[]{3f, 1f});

        addTableHeader(percepcionesTable, "CONCEPTO", "IMPORTE");

        BigDecimal totalPercepciones = BigDecimal.ZERO;

        totalPercepciones = addConceptRow(percepcionesTable, "Sueldo Base", prenomina.getSueldoBase(), totalPercepciones);
        totalPercepciones = addConceptRow(percepcionesTable, "Horas Extras", prenomina.getHorasExtras(), totalPercepciones);
        totalPercepciones = addConceptRow(percepcionesTable, "Bono", prenomina.getBono(), totalPercepciones);
        totalPercepciones = addConceptRow(percepcionesTable, "Comisiones", prenomina.getComisiones(), totalPercepciones);
        totalPercepciones = addConceptRow(percepcionesTable, "Gratificaciones", prenomina.getGratificaciones(), totalPercepciones);
        totalPercepciones = addConceptRow(percepcionesTable, "Aguinaldo Proporcional", prenomina.getAguinaldoProporcional(), totalPercepciones);
        totalPercepciones = addConceptRow(percepcionesTable, "Prima Vacacional", prenomina.getPrimaVacacional(), totalPercepciones);

        addTotalRow(percepcionesTable, "TOTAL PERCEPCIONES", totalPercepciones, new BaseColor(46, 204, 113));

        document.add(percepcionesTable);
    }

    private void addDeduccionesTable(Document document, Prenomina prenomina) throws DocumentException {
        document.add(new Paragraph("DEDUCCIONES", SECTION_FONT));

        PdfPTable deduccionesTable = new PdfPTable(2);
        deduccionesTable.setWidthPercentage(100);
        deduccionesTable.setSpacingAfter(15);
        deduccionesTable.setWidths(new float[]{3f, 1f});

        addTableHeader(deduccionesTable, "CONCEPTO", "IMPORTE");

        BigDecimal totalDeducciones = BigDecimal.ZERO;

        totalDeducciones = addConceptRow(deduccionesTable, "ISR", prenomina.getISR(), totalDeducciones);
        totalDeducciones = addConceptRow(deduccionesTable, "IMSS", prenomina.getIMSS(), totalDeducciones);
        totalDeducciones = addConceptRow(deduccionesTable, "INFONAVIT", prenomina.getINFONAVIT(), totalDeducciones);
        totalDeducciones = addConceptRow(deduccionesTable, "Otras Deducciones", prenomina.getOtrasDeducciones(), totalDeducciones);

        addTotalRow(deduccionesTable, "TOTAL DEDUCCIONES", totalDeducciones, new BaseColor(231, 76, 60));

        document.add(deduccionesTable);
    }

    private void addTableHeader(PdfPTable table, String concept, String amount) {
        PdfPCell conceptHeader = new PdfPCell(new Phrase(concept, HEADER_FONT));
        conceptHeader.setBackgroundColor(new BaseColor(52, 73, 94));
        conceptHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        conceptHeader.setPadding(8);

        PdfPCell amountHeader = new PdfPCell(new Phrase(amount, HEADER_FONT));
        amountHeader.setBackgroundColor(new BaseColor(52, 73, 94));
        amountHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        amountHeader.setPadding(8);

        table.addCell(conceptHeader);
        table.addCell(amountHeader);
    }

    private BigDecimal addConceptRow(PdfPTable table, String concept, BigDecimal amount, BigDecimal total) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            PdfPCell conceptCell = new PdfPCell(new Phrase(concept, CONTENT_FONT));
            conceptCell.setPadding(5);
            conceptCell.setBackgroundColor(new BaseColor(248, 249, 250));

            PdfPCell amountCell = new PdfPCell(new Phrase("$" + String.format("%,.2f", amount), CONTENT_FONT));
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            amountCell.setPadding(5);
            amountCell.setBackgroundColor(new BaseColor(248, 249, 250));

            table.addCell(conceptCell);
            table.addCell(amountCell);

            return total.add(amount);
        }
        return total;
    }

    private void addTotalRow(PdfPTable table, String label, BigDecimal total, BaseColor color) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, HEADER_FONT));
        labelCell.setBackgroundColor(color);
        labelCell.setPadding(8);
        labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell totalCell = new PdfPCell(new Phrase("$" + String.format("%,.2f", total), HEADER_FONT));
        totalCell.setBackgroundColor(color);
        totalCell.setPadding(8);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(labelCell);
        table.addCell(totalCell);
    }

    private void addTotalNeto(Document document, Prenomina prenomina) throws DocumentException {
        PdfPTable netoTable = new PdfPTable(2);
        netoTable.setWidthPercentage(100);
        netoTable.setSpacingAfter(20);
        netoTable.setWidths(new float[]{3f, 1f});

        PdfPCell labelCell = new PdfPCell(new Phrase("TOTAL NETO A PAGAR",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.WHITE)));
        labelCell.setBackgroundColor(new BaseColor(41, 128, 185));
        labelCell.setPadding(10);
        labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell totalCell = new PdfPCell(new Phrase("$" + String.format("%,.2f", prenomina.getTotalNeto()),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.WHITE)));
        totalCell.setBackgroundColor(new BaseColor(41, 128, 185));
        totalCell.setPadding(10);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        netoTable.addCell(labelCell);
        netoTable.addCell(totalCell);

        document.add(netoTable);
    }

    private void addFooter(Document document) throws DocumentException {
        document.add(new Paragraph(" "));

        Paragraph footer = new Paragraph("Este documento es una representación impresa de un recibo de nómina electrónico.",
                FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(20);

        document.add(footer);

        Paragraph timestamp = new Paragraph("Generado el: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY));
        timestamp.setAlignment(Element.ALIGN_CENTER);

        document.add(timestamp);
    }
}