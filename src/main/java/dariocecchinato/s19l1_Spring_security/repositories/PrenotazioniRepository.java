package dariocecchinato.s19l1_Spring_security.repositories;

import dariocecchinato.s19l1_Spring_security.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {

    boolean existsByDipendenteIdAndViaggioDataViaggio(UUID dipendenteId, LocalDate dataViaggio);

    boolean existsByDipendenteIdAndViaggioDataViaggioAndIdNot(UUID dipendenteId, LocalDate dataViaggio, UUID prenotazioneId);
}
