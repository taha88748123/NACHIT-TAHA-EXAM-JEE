package ma.nachit.taha.assurance_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.NiveauCouverture;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratSanteDTO extends ContratDTO {

    private NiveauCouverture niveauCouverture;

    private Integer nombrePersonnesCouvertes;
}
