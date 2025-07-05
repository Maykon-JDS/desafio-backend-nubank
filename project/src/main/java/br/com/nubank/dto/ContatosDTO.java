package br.com.nubank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: IMPLEMENTAR COM RECORD
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContatosDTO {

    @NotBlank(message = "Id do Cliente é Obrigatório!")
    private Long clienteId;

    private String tipo;

    private String conteudo;

}
