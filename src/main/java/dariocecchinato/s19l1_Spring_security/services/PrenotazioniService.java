package dariocecchinato.s19l1_Spring_security.services;

import dariocecchinato.s19l1_Spring_security.entities.Dipendente;
import dariocecchinato.s19l1_Spring_security.entities.Prenotazione;
import dariocecchinato.s19l1_Spring_security.entities.Viaggio;
import dariocecchinato.s19l1_Spring_security.exceptions.BadRequestException;
import dariocecchinato.s19l1_Spring_security.exceptions.NotFoundException;
import dariocecchinato.s19l1_Spring_security.payloads.PrenotazionePayloadDTO;
import dariocecchinato.s19l1_Spring_security.repositories.DipendentiReporitory;
import dariocecchinato.s19l1_Spring_security.repositories.PrenotazioniRepository;
import dariocecchinato.s19l1_Spring_security.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    @Autowired
    private ViaggiRepository viaggiRepository;
    @Autowired
    private DipendentiReporitory dipendentiReporitory;

    public Prenotazione creaPrenotazione(PrenotazionePayloadDTO body){
        UUID dipendenteId = body.dipendente_id();
        Viaggio viaggio = viaggiRepository.findById(body.viaggio_id()).orElseThrow(()-> new NotFoundException(body.viaggio_id()));
        LocalDate dataViaggio = viaggio.getDataViaggio();

        if (prenotazioniRepository.existsByDipendenteIdAndViaggioDataViaggio(dipendenteId,dataViaggio)){
            throw new BadRequestException("Il dipendente " + body.dipendente_id() + " ha già un viaggio prenotato per quella data");
        }else{

        Dipendente dipendente = dipendentiReporitory.findById(body.dipendente_id()).orElseThrow(()->new NotFoundException(body.dipendente_id()));
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataRichiesta(LocalDate.now());
        prenotazione.setDettagli(body.dettagli());
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        return prenotazioniRepository.save(prenotazione);
        }
    }

    public Page<Prenotazione> findAll (int page, int size, String sortby){
        if (page > 10) page =10;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortby));
        return this.prenotazioniRepository.findAll(pageable);
    }

    public Prenotazione findById(UUID prenotazioneId){
        return this.prenotazioniRepository.findById(prenotazioneId).orElseThrow(()->new NotFoundException(prenotazioneId));
    }

    public Prenotazione updatePrenotazione(Prenotazione prenotazione) {
        return prenotazioniRepository.save(prenotazione);
    }

    public void findByIdAndDelete(UUID prenotazioneId){
        Prenotazione found = this.prenotazioniRepository.findById(prenotazioneId).orElseThrow(()-> new NotFoundException(prenotazioneId));
        this.prenotazioniRepository.delete(found);
    }
    public boolean esistePrenotazionePerGiornoEDipendente(UUID dipendenteId, LocalDate dataViaggio, UUID prenotazioneId) {
        return prenotazioniRepository.existsByDipendenteIdAndViaggioDataViaggioAndIdNot(dipendenteId, dataViaggio, prenotazioneId);
    }


}
