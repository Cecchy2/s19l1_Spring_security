package dariocecchinato.s19l1_Spring_security.repositories;

import dariocecchinato.s19l1_Spring_security.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {



Optional<Viaggio> findByDestinazioneAndDataViaggio(String destinazione, LocalDate dataViaggio);
}

