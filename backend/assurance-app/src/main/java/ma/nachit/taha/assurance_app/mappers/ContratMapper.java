package ma.nachit.taha.assurance_app.mappers;

import ma.nachit.taha.assurance_app.dtos.*;
import ma.nachit.taha.assurance_app.entities.*;
import org.springframework.stereotype.Component;

@Component
public class ContratMapper {

    public ContratDTO toDto(Contrat contrat) {
        if (contrat instanceof ContratAutomobile auto) {
            ContratAutomobileDTO dto = new ContratAutomobileDTO();
            copyCommonFieldsToDto(auto, dto);
            dto.setTypeContrat("AUTO");
            dto.setNumImmatriculation(auto.getNumImmatriculation());
            dto.setMarque(auto.getMarque());
            dto.setModele(auto.getModele());
            return dto;
        }
        if (contrat instanceof ContratHabitation habitation) {
            ContratHabitationDTO dto = new ContratHabitationDTO();
            copyCommonFieldsToDto(habitation, dto);
            dto.setTypeContrat("HABITATION");
            dto.setTypeLogement(habitation.getTypeLogement());
            dto.setAdresse(habitation.getAdresse());
            dto.setSuperficie(habitation.getSuperficie());
            return dto;
        }
        if (contrat instanceof ContratSante sante) {
            ContratSanteDTO dto = new ContratSanteDTO();
            copyCommonFieldsToDto(sante, dto);
            dto.setTypeContrat("SANTE");
            dto.setNiveauCouverture(sante.getNiveauCouverture());
            dto.setNombrePersonnesCouvertes(sante.getNombrePersonnesCouvertes());
            return dto;
        }
        return null;
    }

    public Contrat toEntity(ContratDTO dto) {
        if (dto instanceof ContratAutomobileDTO autoDto) {
            ContratAutomobile entity = new ContratAutomobile();
            copyCommonFieldsToEntity(autoDto, entity);
            entity.setNumImmatriculation(autoDto.getNumImmatriculation());
            entity.setMarque(autoDto.getMarque());
            entity.setModele(autoDto.getModele());
            return entity;
        }
        if (dto instanceof ContratHabitationDTO habitationDto) {
            ContratHabitation entity = new ContratHabitation();
            copyCommonFieldsToEntity(habitationDto, entity);
            entity.setTypeLogement(habitationDto.getTypeLogement());
            entity.setAdresse(habitationDto.getAdresse());
            entity.setSuperficie(habitationDto.getSuperficie());
            return entity;
        }
        if (dto instanceof ContratSanteDTO santeDto) {
            ContratSante entity = new ContratSante();
            copyCommonFieldsToEntity(santeDto, entity);
            entity.setNiveauCouverture(santeDto.getNiveauCouverture());
            entity.setNombrePersonnesCouvertes(santeDto.getNombrePersonnesCouvertes());
            return entity;
        }
        return null;
    }

    private void copyCommonFieldsToDto(Contrat src, ContratDTO dto) {
        dto.setId(src.getId());
        dto.setDateSouscription(src.getDateSouscription());
        dto.setStatut(src.getStatut());
        dto.setDateValidation(src.getDateValidation());
        dto.setMontantCotisation(src.getMontantCotisation());
        dto.setDureeContrat(src.getDureeContrat());
        dto.setTauxCouverture(src.getTauxCouverture());
        if (src.getClient() != null) {
            dto.setClientId(src.getClient().getId());
        }
    }

    private void copyCommonFieldsToEntity(ContratDTO dto, Contrat entity) {
        entity.setId(dto.getId());
        entity.setDateSouscription(dto.getDateSouscription());
        entity.setStatut(dto.getStatut());
        entity.setDateValidation(dto.getDateValidation());
        entity.setMontantCotisation(dto.getMontantCotisation());
        entity.setDureeContrat(dto.getDureeContrat());
        entity.setTauxCouverture(dto.getTauxCouverture());
    }
}
