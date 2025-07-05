package br.com.nubank.service;

import br.com.nubank.dto.ClientesDTO;
import br.com.nubank.dto.ClientesResponseDTO;
import br.com.nubank.dto.ContatosResponseDTO;
import br.com.nubank.mapper.ClientesMapper;
import br.com.nubank.mapper.ContatosMapper;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.repository.ClientesRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Lazy
    private final ContatosService contatosService;

    private final ClientesRepository clientesRepository;

    public ClientesService (@Lazy ContatosService contatosService, ClientesRepository clientesRepository){
        this.contatosService = contatosService;
        this.clientesRepository = clientesRepository;
    }

    public Clientes salvarCliente(ClientesDTO clienteDTO) {

        Clientes cliente = new Clientes();
        cliente.setNome(clienteDTO.getNome());

        if (clienteDTO.getContatos() != null && !clienteDTO.getContatos().isEmpty()) {
            List<Contatos> contatos = clienteDTO.getContatos().stream().map(contatosDTO -> ContatosMapper.toEntity(contatosDTO, cliente)).toList();
            cliente.setContatos(contatos);
        }

        return clientesRepository.save(cliente);
    }

    public List<ContatosResponseDTO> listarContatos(Long clienteId) {
        Clientes cliente = this.findById(clienteId);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        if (cliente.getContatos() == null || cliente.getContatos().isEmpty()){
            return new ArrayList<ContatosResponseDTO>();
        }

        return cliente.getContatos().stream().map(ContatosMapper::toResponseDTO).toList();
    }

    public List<ClientesResponseDTO> listarTodos() {
        return clientesRepository.findAll().stream().map(ClientesMapper::toResponseDTO).toList();
    }

    public Clientes findById(Long clienteId) {
        Optional<Clientes> cliente = clientesRepository.findById(clienteId);
        return cliente.orElse(null);
    }
}
