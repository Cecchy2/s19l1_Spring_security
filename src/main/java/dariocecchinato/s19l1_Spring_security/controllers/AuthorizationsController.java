package dariocecchinato.s19l1_Spring_security.controllers;

import dariocecchinato.s19l1_Spring_security.entities.Dipendente;
import dariocecchinato.s19l1_Spring_security.exceptions.BadRequestException;
import dariocecchinato.s19l1_Spring_security.payloads.DipendenteLoginResponseDTO;
import dariocecchinato.s19l1_Spring_security.payloads.DipendentePayloadDTO;
import dariocecchinato.s19l1_Spring_security.payloads.DipendenteResponseDTO;
import dariocecchinato.s19l1_Spring_security.payloads.DipendentiLoginDTO;
import dariocecchinato.s19l1_Spring_security.services.AuthorizationsService;
import dariocecchinato.s19l1_Spring_security.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO save(@RequestBody @Validated DipendentePayloadDTO body, BindingResult validationResult) {
        // @Validated serve per 'attivare' le regole di validazione descritte nel DTO
        // BindingResult mi permette di capire se ci sono stati errori e quali errori ci sono stati

        if (validationResult.hasErrors()) {
            // Se ci sono stati errori lanciamo un'eccezione custom
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            // Se non ci sono stati salviamo l'utente

            return new DipendenteResponseDTO(this.dipendentiService.save(body).getId());
        }

    }
}
