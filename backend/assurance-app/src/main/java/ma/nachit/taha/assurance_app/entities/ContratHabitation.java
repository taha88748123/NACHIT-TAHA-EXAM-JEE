package ma.nachit.taha.assurance_app.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.TypeLogement;

@Entity
@DiscriminatorValue("HABITATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratHabitation extends Contrat {

    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;

    private String adresse;

    private Double superficie;
}
