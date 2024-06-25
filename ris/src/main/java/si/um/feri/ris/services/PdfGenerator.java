package si.um.feri.ris.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Pacient;
import si.um.feri.ris.models.Zdravnik;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerator {

    public static ByteArrayOutputStream generateAppointmentPdf(Pacient pacient, Zdravnik zdravnik, String appointmentDate) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Appointment Details"));
        document.add(new Paragraph("Patient: " + pacient.getIme() + " " + pacient.getPriimek()));
        document.add(new Paragraph("Doctor: " + zdravnik.getIme() + " " + zdravnik.getPriimek()));
        document.add(new Paragraph("Date: " + appointmentDate));
        document.add(new Paragraph("Status: Booked"));
        document.close();
        return baos;
    }
}
