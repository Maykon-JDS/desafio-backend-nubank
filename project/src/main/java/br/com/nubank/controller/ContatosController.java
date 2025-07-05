package br.com.nubank.controller;

import br.com.nubank.dto.ClientesResponseDTO;
import br.com.nubank.dto.ContatosDTO;
import br.com.nubank.dto.ContatosResponseDTO;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.service.ClientesService;
import br.com.nubank.service.ContatosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContatosController {

    private final ClientesService clientesService;
    private final ContatosService contatosService;

    @PostMapping
    public ResponseEntity<Contatos> criar(@RequestBody ContatosDTO contatoDTO) {

        if (clientesService.findById(contatoDTO.getClienteId()) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Contatos contatoSalvo = contatosService.salvarContato(contatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarTotos() {
        return ResponseEntity.ok().body(clientesService.listarTodos());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatosResponseDTO>> listarContatos(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clientesService.listarContatos(clienteId));
    }

}
