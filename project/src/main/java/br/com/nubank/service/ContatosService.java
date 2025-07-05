package br.com.nubank.service;


import br.com.nubank.dto.ContatosDTO;
import br.com.nubank.dto.ContatosResponseDTO;
import br.com.nubank.mapper.ContatosMapper;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.repository.ContatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatosService {

    private final ClientesService clientesService;
    private final ContatosRepository contatosRepository;

    public Contatos salvarContato(ContatosDTO dto) {

        Clientes cliente = clientesService.findById(dto.getClienteId());

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Contatos contato = new Contatos();
        contato.setClientes(cliente);
        contato.setTipo(dto.getTipo());
        contato.setConteudo(dto.getConteudo());

        return contatosRepository.save(contato);
    }

    public List<ContatosResponseDTO> listarTodos() {
        return contatosRepository.findAll().stream().map(ContatosMapper::toResponseDTO).toList();
    }
}
