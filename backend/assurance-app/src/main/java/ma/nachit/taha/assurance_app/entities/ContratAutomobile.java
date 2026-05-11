package ma.nachit.taha.assurance_app.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("AUTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratAutomobile extends Contrat {

    private String numImmatriculation;

    private String marque;

    private String modele;
}
