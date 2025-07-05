package br.com.nubank.mapper;

import br.com.nubank.dto.ClientesResponseDTO;
import br.com.nubank.dto.ContatosChildResponseDTO;
import br.com.nubank.model.Clientes;

import java.util.List;

public class ClientesMapper {

    public static ClientesResponseDTO toResponseDTO (Clientes cliente) {
        ClientesResponseDTO clienteDTO = new ClientesResponseDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());

        if (cliente.getContatos() != null && !cliente.getContatos().isEmpty()) {
            List<ContatosChildResponseDTO> contatosDTO = cliente.getContatos().stream().map(ContatosMapper::toChildResponseDTO).toList();
            clienteDTO.setContatos(contatosDTO);
        }

        return clienteDTO;
    }
}
