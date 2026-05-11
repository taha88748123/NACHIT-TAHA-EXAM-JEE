package ma.nachit.taha.assurance_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.TypePaiement;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypePaiement type;

    @ManyToOne
    private Contrat contrat;
}
