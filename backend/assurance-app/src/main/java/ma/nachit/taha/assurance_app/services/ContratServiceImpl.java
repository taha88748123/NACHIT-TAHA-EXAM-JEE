package ma.nachit.taha.assurance_app.services;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.assurance_app.dtos.*;
import ma.nachit.taha.assurance_app.entities.*;
import ma.nachit.taha.assurance_app.enums.StatutContrat;
import ma.nachit.taha.assurance_app.mappers.ContratMapper;
import ma.nachit.taha.assurance_app.repositories.ClientRepository;
import ma.nachit.taha.assurance_app.repositories.ContratRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContratServiceImpl implements ContratService {

    private final ContratRepository contratRepository;
    private final ClientRepository clientRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratDTO createContrat(ContratDTO contratDTO) {
        Contrat contrat = contratMapper.toEntity(contratDTO);
        contrat.setId(null);

        Client client = clientRepository.findById(contratDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable avec id : " + contratDTO.getClientId()));
        contrat.setClient(client);

        Contrat saved = contratRepository.save(contrat);
        return contratMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ContratDTO getContratById(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec id : " + id));
        return contratMapper.toDto(contrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(contratMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> getContratsByStatut(StatutContrat statut) {
        return contratRepository.findByStatut(statut).stream()
                .map(contratMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> getContratsByClientId(Long clientId) {
        return contratRepository.findByClientId(clientId).stream()
                .map(contratMapper::toDto)
                .toList();
    }

    @Override
    public ContratDTO updateContrat(Long id, ContratDTO contratDTO) {
        Contrat existing = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec id : " + id));

        existing.setDateSouscription(contratDTO.getDateSouscription());
        existing.setStatut(contratDTO.getStatut());
        existing.setDateValidation(contratDTO.getDateValidation());
        existing.setMontantCotisation(contratDTO.getMontantCotisation());
        existing.setDureeContrat(contratDTO.getDureeContrat());
        existing.setTauxCouverture(contratDTO.getTauxCouverture());

        if (existing instanceof ContratAutomobile auto && contratDTO instanceof ContratAutomobileDTO autoDto) {
            auto.setNumImmatriculation(autoDto.getNumImmatriculation());
            auto.setMarque(autoDto.getMarque());
            auto.setModele(autoDto.getModele());
        } else if (existing instanceof ContratHabitation habitation && contratDTO instanceof ContratHabitationDTO habitationDto) {
            habitation.setTypeLogement(habitationDto.getTypeLogement());
            habitation.setAdresse(habitationDto.getAdresse());
            habitation.setSuperficie(habitationDto.getSuperficie());
        } else if (existing instanceof ContratSante sante && contratDTO instanceof ContratSanteDTO santeDto) {
            sante.setNiveauCouverture(santeDto.getNiveauCouverture());
            sante.setNombrePersonnesCouvertes(santeDto.getNombrePersonnesCouvertes());
        }

        Contrat saved = contratRepository.save(existing);
        return contratMapper.toDto(saved);
    }

    @Override
    public ContratDTO validerContrat(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec id : " + id));
        contrat.setStatut(StatutContrat.VALIDE);
        contrat.setDateValidation(LocalDate.now());
        Contrat saved = contratRepository.save(contrat);
        return contratMapper.toDto(saved);
    }

    @Override
    public void deleteContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new RuntimeException("Contrat introuvable avec id : " + id);
        }
        contratRepository.deleteById(id);
    }
}
