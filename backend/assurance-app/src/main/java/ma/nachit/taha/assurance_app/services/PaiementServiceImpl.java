package ma.nachit.taha.assurance_app.services;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.assurance_app.dtos.PaiementDTO;
import ma.nachit.taha.assurance_app.entities.Contrat;
import ma.nachit.taha.assurance_app.entities.Paiement;
import ma.nachit.taha.assurance_app.mappers.PaiementMapper;
import ma.nachit.taha.assurance_app.repositories.ContratRepository;
import ma.nachit.taha.assurance_app.repositories.PaiementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementDTO createPaiement(PaiementDTO paiementDTO) {
        Paiement paiement = paiementMapper.toEntity(paiementDTO);
        paiement.setId(null);

        Contrat contrat = contratRepository.findById(paiementDTO.getContratId())
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec id : " + paiementDTO.getContratId()));
        paiement.setContrat(contrat);

        Paiement saved = paiementRepository.save(paiement);
        return paiementMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementDTO getPaiementById(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec id : " + id));
        return paiementMapper.toDto(paiement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> getPaiementsByContratId(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public void deletePaiement(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new RuntimeException("Paiement introuvable avec id : " + id);
        }
        paiementRepository.deleteById(id);
    }
}
