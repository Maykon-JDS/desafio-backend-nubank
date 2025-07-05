package br.com.nubank.dto.Clientes;

import br.com.nubank.dto.Contatos.ContatosChildResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientesResponseDTO {

    private Long id;
    private String nome;
    private List<ContatosChildResponseDTO> contatos;

}
