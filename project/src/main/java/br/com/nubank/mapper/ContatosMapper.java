package br.com.nubank.mapper;

import br.com.nubank.dto.Contatos.ContatosChildDTO;
import br.com.nubank.dto.Contatos.ContatosChildResponseDTO;
import br.com.nubank.dto.Contatos.ContatosDTO;
import br.com.nubank.dto.Contatos.ContatosResponseDTO;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;

public class ContatosMapper {

    public static ContatosDTO toDTO (Contatos contato) {
        ContatosDTO dto = new ContatosDTO();

        dto.setTipo(contato.getTipo());
        dto.setConteudo(contato.getConteudo());
        dto.setClienteId(contato.getClientes().getId());

        return dto;
    }

    public static ContatosResponseDTO toResponseDTO (Contatos contato) {
        ContatosResponseDTO dto = new ContatosResponseDTO();

        dto.setId(contato.getId());
        dto.setTipo(contato.getTipo());
        dto.setSituacao(contato.getSituacao());
        dto.setConteudo(contato.getConteudo());
        dto.setCreatedAt(contato.getCreatedAt());
        dto.setUpdatedAt(contato.getUpdatedAt());
        dto.setClienteId(contato.getClientes().getId());

        return dto;
    }

    public static ContatosChildResponseDTO toChildResponseDTO (Contatos contato) {
        ContatosChildResponseDTO dto = new ContatosChildResponseDTO();

        dto.setId(contato.getId());
        dto.setTipo(contato.getTipo());
        dto.setSituacao(contato.getSituacao());
        dto.setConteudo(contato.getConteudo());
        dto.setCreatedAt(contato.getCreatedAt());
        dto.setUpdatedAt(contato.getUpdatedAt());

        return dto;
    }

    public static Contatos toEntity(ContatosChildDTO dto, Clientes cliente) {

        Contatos contato = new Contatos();
        contato.setClientes(cliente);
        contato.setTipo(dto.getTipo());
        contato.setConteudo(dto.getConteudo());

        return contato;
    }
}
