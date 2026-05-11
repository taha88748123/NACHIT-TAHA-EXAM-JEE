package ma.nachit.taha.assurance_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratAutomobileDTO extends ContratDTO {

    private String numImmatriculation;

    private String marque;

    private String modele;
}
