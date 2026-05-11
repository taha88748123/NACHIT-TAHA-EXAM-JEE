package ma.nachit.taha.assurance_app.web;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.assurance_app.dtos.PaiementDTO;
import ma.nachit.taha.assurance_app.services.PaiementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PaiementRestController {

    private final PaiementService paiementService;

    @GetMapping
    public List<PaiementDTO> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    public PaiementDTO getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    @GetMapping("/contrat/{contratId}")
    public List<PaiementDTO> getPaiementsByContrat(@PathVariable Long contratId) {
        return paiementService.getPaiementsByContratId(contratId);
    }

    @PostMapping
    public PaiementDTO createPaiement(@RequestBody PaiementDTO paiementDTO) {
        return paiementService.createPaiement(paiementDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }
}
