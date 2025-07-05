package br.com.nubank.controller;

import br.com.nubank.dto.ClientesDTO;
import br.com.nubank.dto.ClientesResponseDTO;
import br.com.nubank.dto.ContatosResponseDTO;
import br.com.nubank.mapper.ClientesMapper;
import br.com.nubank.model.Clientes;
import br.com.nubank.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService clientesService;

    @PostMapping
    public ResponseEntity<ClientesResponseDTO> criar(@RequestBody ClientesDTO clienteDTO) {
        Clientes clienteSalvo = clientesService.salvarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientesMapper.toResponseDTO(clienteSalvo));
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarTotos() {
        return ResponseEntity.ok().body(clientesService.listarTodos());
    }

    @GetMapping("/{clienteId}/contatos")
    public ResponseEntity<List<ContatosResponseDTO>> listarContatos(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clientesService.listarContatos(clienteId));
    }

}
