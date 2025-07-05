package br.com.nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContatosChildResponseDTO {

    private Long id;

    private String tipo;

    private String conteudo;

    private Boolean situacao;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
