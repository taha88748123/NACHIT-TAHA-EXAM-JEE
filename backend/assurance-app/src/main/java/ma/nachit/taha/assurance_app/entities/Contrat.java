package ma.nachit.taha.assurance_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.StatutContrat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_contrat", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateSouscription;

    @Enumerated(EnumType.STRING)
    private StatutContrat statut;

    private LocalDate dateValidation;

    private Double montantCotisation;

    private Integer dureeContrat;

    private Double tauxCouverture;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "contrat")
    private List<Paiement> paiements;
}
