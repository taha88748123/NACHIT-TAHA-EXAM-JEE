package ma.nachit.taha.assurance_app.services;

import ma.nachit.taha.assurance_app.dtos.PaiementDTO;

import java.util.List;

public interface PaiementService {

    PaiementDTO createPaiement(PaiementDTO paiementDTO);

    PaiementDTO getPaiementById(Long id);

    List<PaiementDTO> getAllPaiements();

    List<PaiementDTO> getPaiementsByContratId(Long contratId);

    void deletePaiement(Long id);
}
