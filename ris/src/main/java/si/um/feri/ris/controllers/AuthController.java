package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.models.Zdravnik;
import si.um.feri.ris.repositories.UporabnikRepository;
import si.um.feri.ris.repositories.ZdravnikRepository;
import si.um.feri.ris.services.UporabnikService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UporabnikService uporabnikService;

    @Autowired
    private ZdravnikRepository zdravnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Uporabnik> registerUporabnik(@RequestBody Uporabnik uporabnik) {
        Uporabnik newUser = uporabnikService.registerUporabnik(uporabnik);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Uporabnik> login(@RequestBody Uporabnik uporabnik) {
        Uporabnik existingUser = uporabnikService.getUporabnikByUporabniskoIme(uporabnik.getUporabniskoIme());
        if (existingUser != null && passwordEncoder.matches(uporabnik.getGeslo(), existingUser.getGeslo())) {
            // Generate token
            String token = generateToken(existingUser);
            existingUser.setToken(token);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    // Pretpostavljena metoda za generiranje tokena
    private String generateToken(Uporabnik uporabnik) {
        // Logika za generiranje tokena (ovo je samo primjer)
        return "some-generated-token"; // Zamijenite stvarnom logikom generiranja tokena
    }
}
