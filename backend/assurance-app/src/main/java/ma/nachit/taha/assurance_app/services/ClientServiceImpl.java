package ma.nachit.taha.assurance_app.services;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.assurance_app.dtos.ClientDTO;
import ma.nachit.taha.assurance_app.entities.Client;
import ma.nachit.taha.assurance_app.mappers.ClientMapper;
import ma.nachit.taha.assurance_app.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        client.setId(null);
        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec id : " + id));
        return clientMapper.toDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec id : " + id));
        existing.setNom(clientDTO.getNom());
        existing.setEmail(clientDTO.getEmail());
        Client saved = clientRepository.save(existing);
        return clientMapper.toDto(saved);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client introuvable avec id : " + id);
        }
        clientRepository.deleteById(id);
    }
}
