package br.com.nubank.service;

import br.com.nubank.dto.Contatos.ContatosDTO;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.repository.ClientesRepository;
import br.com.nubank.repository.ContatosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContatosServiceTest {

    @Mock
    ContatosRepository contatosRepository;

    @Mock
    ClientesService clientesService;

    @InjectMocks
    ContatosService contatosService;

    @Test
    void salvarContato() {

        Clientes cliente = new Clientes();
        cliente.setId(1L);

        when(clientesService.findById(1L)).thenReturn(cliente);
        when(contatosRepository.save(any(Contatos.class))).thenAnswer(invocation -> {
           Contatos contato = invocation.getArgument(0);
           return  contato;
        });

        ContatosDTO contatoDTO = new ContatosDTO();
        contatoDTO.setClienteId(1L);
        contatoDTO.setTipo("EMAIL");
        contatoDTO.setConteudo("maria_correia8@hotmail.com");

        Contatos result = contatosService.salvarContato(contatoDTO);

        assertNotNull(result);
        assertEquals(cliente, result.getClientes());

        assertEquals("EMAIL", result.getTipo());
        assertEquals("maria_correia8@hotmail.com", result.getConteudo());
    }

}
