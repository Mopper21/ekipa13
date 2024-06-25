package si.um.feri.ris.controllers;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.*;
import si.um.feri.ris.repositories.*;
import si.um.feri.ris.services.EmailSender;
import si.um.feri.ris.services.PdfGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/termin")
public class TerminController {

    @Autowired
    private TerminRepository terminRepository;

    @Autowired
    private ZdravnikRepository zdravnikRepository;

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping
    public List<TerminDTO> getAllTermini() {
        List<Termin> termini = terminRepository.findAll();
        return termini.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminDTO> getTerminById(@PathVariable Long id) {
        Optional<Termin> termin = terminRepository.findById(id);
        return termin.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/zdravniki/{id}/termin")
    public ResponseEntity<List<TerminDTO>> getTerminiByZdravnikId(@PathVariable Long id) {
        List<Termin> termini = terminRepository.findByZdravnikId(id);
        List<TerminDTO> terminDTOs = termini.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(terminDTOs);
    }

    private TerminDTO convertToDto(Termin termin) {
        TerminDTO dto = new TerminDTO();
        dto.setId(termin.getId());
        dto.setDatum(termin.getDatum().toString());
        dto.setStatus(termin.getStatus());

        Zdravnik zdravnik = termin.getZdravnik();
        if (zdravnik != null) {
            dto.setZdravnikId(zdravnik.getId());
            dto.setZdravnikIme(zdravnik.getIme());
            dto.setZdravnikPriimek(zdravnik.getPriimek());
        }

        Pacient pacient = termin.getPacient();
        if (pacient != null) {
            dto.setPacientId(pacient.getId());
            dto.setPacientIme(pacient.getIme());
            dto.setPacientPriimek(pacient.getPriimek());
        }

        return dto;
    }

    @PostMapping
    public ResponseEntity<Termin> createTermin(@RequestBody Termin termin) {
        System.out.println("Received request to create termin: " + termin);
        Optional<Zdravnik> zdravnik = zdravnikRepository.findById(termin.getZdravnik().getId());
        if (zdravnik.isPresent()) {
            termin.setZdravnik(zdravnik.get());
            Termin savedTermin = terminRepository.save(termin);
            System.out.println("Termin saved successfully: " + savedTermin);
            return ResponseEntity.ok(savedTermin);
        } else {
            System.out.println("Zdravnik not found with id: " + termin.getZdravnik().getId());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerminDTO> updateTermin(@PathVariable Long id, @RequestBody Termin terminDetails) {
        Optional<Termin> terminOptional = terminRepository.findById(id);
        if (terminOptional.isPresent()) {
            Termin termin = terminOptional.get();
            Optional<Pacient> pacientOptional = pacientRepository.findById(terminDetails.getPacient().getId());
            if (pacientOptional.isPresent()) {
                Pacient pacient = pacientOptional.get();
                termin.setPacient(pacient);
                termin.setStatus(terminDetails.getStatus());
                terminRepository.save(termin);

                Zdravnik zdravnik = termin.getZdravnik();
                String appointmentDate = termin.getDatum().toString();

                try {
                    // Generate PDF
                    ByteArrayOutputStream pacientPdf = PdfGenerator.generateAppointmentPdf(pacient, zdravnik, appointmentDate);
                    ByteArrayOutputStream doctorPdf = PdfGenerator.generateAppointmentPdf(pacient, zdravnik, appointmentDate);

                    // Send emails
                    emailSender.sendEmailWithAttachment(
                            pacient.getEmail(),  // Patient's email
                            "Appointment Confirmation",
                            "Dear " + pacient.getIme() + ",\n\nYour appointment has been confirmed.",
                            pacientPdf,
                            "appointment-confirmation.pdf"
                    );

                    emailSender.sendEmailWithAttachment(
                            zdravnik.getEmail(),  // Doctor's email
                            "New Appointment Booking",
                            "Dear Dr. " + zdravnik.getIme() + ",\n\nA new appointment has been booked.",
                            doctorPdf,
                            "appointment-details.pdf"
                    );
                } catch (jakarta.mail.MessagingException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }

                return ResponseEntity.ok(convertToDto(termin));
            } else {
                return ResponseEntity.status(500).body(null); // Pacient not found
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTermin(@PathVariable Long id) {
        if (terminRepository.existsById(id)) {
            terminRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
