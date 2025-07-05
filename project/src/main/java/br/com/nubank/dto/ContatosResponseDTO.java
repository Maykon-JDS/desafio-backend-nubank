package br.com.nubank.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContatosResponseDTO {

    private Long id;

    private String tipo;

    private String conteudo;

    private Boolean situacao;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long clienteId;

}
