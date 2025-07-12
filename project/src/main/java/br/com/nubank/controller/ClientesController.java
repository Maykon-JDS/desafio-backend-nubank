package br.com.nubank.controller;

import br.com.nubank.dto.Clientes.ClientesDTO;
import br.com.nubank.dto.Clientes.ClientesRecordDTO;
import br.com.nubank.dto.Clientes.ClientesResponseDTO;
import br.com.nubank.dto.Contatos.ContatosResponseDTO;
import br.com.nubank.mapper.ClientesMapper;
import br.com.nubank.model.Clientes;
import br.com.nubank.service.ClientesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService clientesService;

    @PostMapping
    @Operation(description = "Endpoint responsável por criar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos para criação", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro de requisição", content = @Content),
    })
    public ResponseEntity<ClientesResponseDTO> criar(@RequestBody ClientesRecordDTO clientesRecordDTO) {
        Clientes clienteSalvo = clientesService.salvarCliente(clientesRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientesMapper.toResponseDTO(clienteSalvo));
    }

    @GetMapping
    @Operation(description = "Endpoint responsável por listar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
    })
    public ResponseEntity<List<ClientesResponseDTO>> listarTotos() {
        return ResponseEntity.ok().body(clientesService.listarTodos());
    }

    @GetMapping("/{clienteId}/contatos")
    @Operation(description = "Endpoint responsável por listar todos os contatos de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
    })
    public ResponseEntity<List<ContatosResponseDTO>> listarContatos(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clientesService.listarContatos(clienteId));
    }

}
