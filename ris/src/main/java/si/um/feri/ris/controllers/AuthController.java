package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.repositories.UporabnikRepository;
import si.um.feri.ris.services.UporabnikService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UporabnikService uporabnikService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Uporabnik> registerUporabnik(@RequestBody Uporabnik uporabnik) {
        Uporabnik newUser = uporabnikService.registerUporabnik(uporabnik);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Uporabnik uporabnik) {
        Uporabnik existingUser = uporabnikService.getUporabnikByUporabniskoIme(uporabnik.getUporabniskoIme());
        if (existingUser != null && passwordEncoder.matches(uporabnik.getGeslo(), existingUser.getGeslo())) {
            return ResponseEntity.ok("Prijava uspešna");
        } else {
            return ResponseEntity.status(401).body("Napačno uporabniško ime ali geslo");
        }
    }
}
