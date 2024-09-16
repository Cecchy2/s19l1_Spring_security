package dariocecchinato.s19l1_Spring_security.controllers;

import dariocecchinato.s19l1_Spring_security.entities.Dipendente;
import dariocecchinato.s19l1_Spring_security.payloads.DipendenteLoginResponseDTO;
import dariocecchinato.s19l1_Spring_security.payloads.DipendenteResponseDTO;
import dariocecchinato.s19l1_Spring_security.payloads.DipendentiLoginDTO;
import dariocecchinato.s19l1_Spring_security.services.AuthorizationsService;
import dariocecchinato.s19l1_Spring_security.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorizations")
public class AuthorizationsController {
    @Autowired
    AuthorizationsService authorizationsService;
    @Autowired
    private DipendentiService dipendentiService;

@PostMapping("/login")
    public DipendenteLoginResponseDTO login(@RequestBody DipendentiLoginDTO body){
    return new DipendenteLoginResponseDTO(this.authorizationsService.checkCredenzialiEGeneraToken(body));
}
}
