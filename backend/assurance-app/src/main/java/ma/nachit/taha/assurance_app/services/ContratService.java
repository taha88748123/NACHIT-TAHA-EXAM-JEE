package ma.nachit.taha.assurance_app.services;

import ma.nachit.taha.assurance_app.dtos.ContratDTO;
import ma.nachit.taha.assurance_app.enums.StatutContrat;

import java.util.List;

public interface ContratService {

    ContratDTO createContrat(ContratDTO contratDTO);

    ContratDTO getContratById(Long id);

    List<ContratDTO> getAllContrats();

    List<ContratDTO> getContratsByStatut(StatutContrat statut);

    List<ContratDTO> getContratsByClientId(Long clientId);

    ContratDTO updateContrat(Long id, ContratDTO contratDTO);

    ContratDTO validerContrat(Long id);

    void deleteContrat(Long id);
}
