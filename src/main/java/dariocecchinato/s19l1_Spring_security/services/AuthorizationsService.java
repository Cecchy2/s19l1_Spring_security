package dariocecchinato.s19l1_Spring_security.services;

import dariocecchinato.s19l1_Spring_security.entities.Dipendente;
import dariocecchinato.s19l1_Spring_security.exceptions.UnauthorizedException;
import dariocecchinato.s19l1_Spring_security.payloads.DipendentiLoginDTO;
import dariocecchinato.s19l1_Spring_security.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationsService {
    @Autowired
    private DipendentiService dipendentiService;
    @Autowired
    private JWTTools jwtTools;

    public String checkCredenzialiEGeneraToken(DipendentiLoginDTO body){
        Dipendente found = this.dipendentiService.findDipendenteByUsername(body.username());
        if (found.getPassword().equals(body.password())){
            return this.jwtTools.createToken(found);
        } else{
            throw new UnauthorizedException("Errore nelle credenziali che hai fornito");
        }
    }
}
