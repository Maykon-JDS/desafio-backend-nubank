package br.com.nubank.dto.Clientes;

import br.com.nubank.dto.Contatos.ContatosChildDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientesDTO {

    private String nome;
    private List<ContatosChildDTO> contatos;
}
