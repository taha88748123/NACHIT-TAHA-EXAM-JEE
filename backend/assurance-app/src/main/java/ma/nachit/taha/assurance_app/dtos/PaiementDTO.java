package ma.nachit.taha.assurance_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.TypePaiement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {

    private Long id;

    private LocalDate date;

    private Double montant;

    private TypePaiement type;

    private Long contratId;
}
