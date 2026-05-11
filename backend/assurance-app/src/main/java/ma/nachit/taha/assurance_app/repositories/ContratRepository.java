package ma.nachit.taha.assurance_app.repositories;

import ma.nachit.taha.assurance_app.entities.Contrat;
import ma.nachit.taha.assurance_app.enums.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

    List<Contrat> findByStatut(StatutContrat statut);

    List<Contrat> findByClientId(Long clientId);
}
