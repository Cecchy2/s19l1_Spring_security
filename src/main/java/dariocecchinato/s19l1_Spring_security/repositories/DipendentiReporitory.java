package dariocecchinato.s19l1_Spring_security.repositories;

import dariocecchinato.s19l1_Spring_security.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendentiReporitory extends JpaRepository<Dipendente, UUID> {

    boolean existsByEmail(String email);
    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByUsername(String username);


}
