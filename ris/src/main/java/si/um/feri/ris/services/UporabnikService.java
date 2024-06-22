package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.repositories.UporabnikRepository;

@Service
public class UporabnikService {

    @Autowired
    private UporabnikRepository uporabnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Uporabnik registerUporabnik(Uporabnik uporabnik) {
        uporabnik.setGeslo(passwordEncoder.encode(uporabnik.getGeslo()));
        return uporabnikRepository.save(uporabnik);
    }

    public Uporabnik getUporabnikByUporabniskoIme(String uporabniskoIme) {
        return uporabnikRepository.findByUporabniskoIme(uporabniskoIme);
    }
}
