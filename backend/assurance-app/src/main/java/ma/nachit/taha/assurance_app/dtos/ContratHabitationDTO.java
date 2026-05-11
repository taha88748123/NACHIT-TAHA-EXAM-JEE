package ma.nachit.taha.assurance_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.nachit.taha.assurance_app.enums.TypeLogement;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratHabitationDTO extends ContratDTO {

    private TypeLogement typeLogement;

    private String adresse;

    private Double superficie;
}
