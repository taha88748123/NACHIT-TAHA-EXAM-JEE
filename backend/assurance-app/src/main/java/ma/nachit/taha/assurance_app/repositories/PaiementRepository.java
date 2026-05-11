package ma.nachit.taha.assurance_app.repositories;

import ma.nachit.taha.assurance_app.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratId(Long contratId);
}
