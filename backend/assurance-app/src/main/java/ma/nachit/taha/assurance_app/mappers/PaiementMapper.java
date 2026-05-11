package ma.nachit.taha.assurance_app.mappers;

import ma.nachit.taha.assurance_app.dtos.PaiementDTO;
import ma.nachit.taha.assurance_app.entities.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public PaiementDTO toDto(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setDate(paiement.getDate());
        dto.setMontant(paiement.getMontant());
        dto.setType(paiement.getType());
        if (paiement.getContrat() != null) {
            dto.setContratId(paiement.getContrat().getId());
        }
        return dto;
    }

    public Paiement toEntity(PaiementDTO dto) {
        Paiement paiement = new Paiement();
        paiement.setId(dto.getId());
        paiement.setDate(dto.getDate());
        paiement.setMontant(dto.getMontant());
        paiement.setType(dto.getType());
        return paiement;
    }
}
