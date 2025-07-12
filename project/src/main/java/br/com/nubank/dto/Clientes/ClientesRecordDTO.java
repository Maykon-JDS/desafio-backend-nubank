package br.com.nubank.dto.Clientes;

import br.com.nubank.dto.Contatos.ContatosChildDTO;

import java.util.List;

public record ClientesRecordDTO(String nome, List<ContatosChildDTO> contatos) {}
