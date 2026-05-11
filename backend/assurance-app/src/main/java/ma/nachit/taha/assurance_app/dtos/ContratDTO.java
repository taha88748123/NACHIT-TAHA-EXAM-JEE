package ma.nachit.taha.assurance_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.StatutContrat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {

    private Long id;

    private LocalDate dateSouscription;

    private StatutContrat statut;

    private LocalDate dateValidation;

    private Double montantCotisation;

    private Integer dureeContrat;

    private Double tauxCouverture;

    private Long clientId;

    private String typeContrat;
}
