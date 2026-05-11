package ma.nachit.taha.assurance_app.web;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.assurance_app.dtos.ContratAutomobileDTO;
import ma.nachit.taha.assurance_app.dtos.ContratDTO;
import ma.nachit.taha.assurance_app.dtos.ContratHabitationDTO;
import ma.nachit.taha.assurance_app.dtos.ContratSanteDTO;
import ma.nachit.taha.assurance_app.enums.StatutContrat;
import ma.nachit.taha.assurance_app.services.ContratService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ContratRestController {

    private final ContratService contratService;

    @GetMapping
    public List<ContratDTO> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public ContratDTO getContratById(@PathVariable Long id) {
        return contratService.getContratById(id);
    }

    @GetMapping("/statut/{statut}")
    public List<ContratDTO> getContratsByStatut(@PathVariable StatutContrat statut) {
        return contratService.getContratsByStatut(statut);
    }

    @GetMapping("/client/{clientId}")
    public List<ContratDTO> getContratsByClient(@PathVariable Long clientId) {
        return contratService.getContratsByClientId(clientId);
    }

    @PostMapping("/automobile")
    public ContratDTO createAutomobile(@RequestBody ContratAutomobileDTO dto) {
        return contratService.createContrat(dto);
    }

    @PostMapping("/habitation")
    public ContratDTO createHabitation(@RequestBody ContratHabitationDTO dto) {
        return contratService.createContrat(dto);
    }

    @PostMapping("/sante")
    public ContratDTO createSante(@RequestBody ContratSanteDTO dto) {
        return contratService.createContrat(dto);
    }

    @PutMapping("/automobile/{id}")
    public ContratDTO updateAutomobile(@PathVariable Long id, @RequestBody ContratAutomobileDTO dto) {
        return contratService.updateContrat(id, dto);
    }

    @PutMapping("/habitation/{id}")
    public ContratDTO updateHabitation(@PathVariable Long id, @RequestBody ContratHabitationDTO dto) {
        return contratService.updateContrat(id, dto);
    }

    @PutMapping("/sante/{id}")
    public ContratDTO updateSante(@PathVariable Long id, @RequestBody ContratSanteDTO dto) {
        return contratService.updateContrat(id, dto);
    }

    @PutMapping("/{id}/valider")
    public ContratDTO validerContrat(@PathVariable Long id) {
        return contratService.validerContrat(id);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }
}
