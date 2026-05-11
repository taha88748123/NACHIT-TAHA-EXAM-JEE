package ma.nachit.taha.assurance_app.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.NiveauCouverture;

@Entity
@DiscriminatorValue("SANTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratSante extends Contrat {

    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;

    private Integer nombrePersonnesCouvertes;
}
