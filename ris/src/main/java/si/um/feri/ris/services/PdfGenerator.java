package si.um.feri.ris.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import si.um.feri.ris.models.Pacient;
import si.um.feri.ris.models.Zdravnik;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {

    public static ByteArrayOutputStream generateAppointmentPdf(Pacient pacient, Zdravnik zdravnik, String appointmentDate) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Appointment Confirmation"));
        document.add(new Paragraph("Pacient: " + pacient.getIme() + " " + pacient.getPriimek()));
        document.add(new Paragraph("Email: " + pacient.getEmail()));
        document.add(new Paragraph("Doctor: " + zdravnik.getIme() + " " + zdravnik.getPriimek()));
        document.add(new Paragraph("Specialization: " + zdravnik.getSpecializacija()));
        document.add(new Paragraph("Appointment Date: " + appointmentDate));

        document.close();
        return outputStream;
    }
}