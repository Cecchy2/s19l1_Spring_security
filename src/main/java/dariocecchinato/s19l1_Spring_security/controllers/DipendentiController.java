package dariocecchinato.s19l1_Spring_security.controllers;

import dariocecchinato.s19l1_Spring_security.entities.Dipendente;
import dariocecchinato.s19l1_Spring_security.exceptions.BadRequestException;
import dariocecchinato.s19l1_Spring_security.exceptions.NotFoundException;
import dariocecchinato.s19l1_Spring_security.payloads.DipendentePayloadDTO;
import dariocecchinato.s19l1_Spring_security.payloads.DipendenteResponseDTO;
import dariocecchinato.s19l1_Spring_security.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;

            @GetMapping
    public Page<Dipendente> findAllDipendenti(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nome")String sortby){
        return this.dipendentiService.findAll(page, size, sortby);
            }

            /*@PostMapping
            @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO saveDipendente(@RequestBody @Validated DipendentePayloadDTO body, BindingResult validationResult){
                if (validationResult.hasErrors()){
                    String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
                    throw new BadRequestException("Ci sono errore con il payload " + message);
                }else {
                    return new DipendenteResponseDTO(this.dipendentiService.save(body).getId());
                }
            }*/

            @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId){
                Dipendente found = this.dipendentiService.findDipendenteById(dipendenteId);
                if (found == null )throw new NotFoundException(dipendenteId);
                return found;
            }
            @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId,@RequestBody @Validated DipendentePayloadDTO body, BindingResult validationResult){
                if (validationResult.hasErrors()){
                    String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
                    throw new BadRequestException("Ci sono errore con il payload " + message);
                }else {
                    return this.dipendentiService.findByIdAndUpdate(dipendenteId,body);
                }
            }

            @DeleteMapping("/{dipendenteId}")
            @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendenteId){this.dipendentiService.findByIdAndDeleteDipendente(dipendenteId);}


    }




