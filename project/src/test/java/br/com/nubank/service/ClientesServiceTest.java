package br.com.nubank.service;

import br.com.nubank.dto.Clientes.ClientesRecordDTO;
import br.com.nubank.dto.Clientes.ClientesResponseDTO;
import br.com.nubank.dto.Contatos.ContatosChildDTO;
import br.com.nubank.dto.Contatos.ContatosResponseDTO;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.repository.ClientesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientesServiceTest {

    @Mock
    ClientesRepository clientesRepository;

    @InjectMocks
    ClientesService clientesService;

    ClientesRecordDTO clientesRecordDTO;
    List<ContatosChildDTO> contatosChildDTOS;

    @BeforeEach
    void setUp(){
        contatosChildDTOS = new ArrayList<>();

        contatosChildDTOS.add(new ContatosChildDTO("TELEFONE", "6621648176"));
        contatosChildDTOS.add(new ContatosChildDTO("TELEFONE", "9632197043"));
        contatosChildDTOS.add(new ContatosChildDTO("EMAIL", "maria_correia8@hotmail.com"));
        contatosChildDTOS.add(new ContatosChildDTO("EMAIL", "alissonsodre58@geradornv.com"));

        clientesRecordDTO = new ClientesRecordDTO("TEST NUBANK 01", contatosChildDTOS);
    }

    @Test
    void salvarCliente(){
        when(clientesRepository.save(any(Clientes.class))).thenAnswer(invocation -> {
            Clientes cliente = invocation.getArgument(0);
            return cliente;
        });


        Clientes cliente = clientesService.salvarCliente(clientesRecordDTO);

        assertEquals("TEST NUBANK 01", cliente.getNome());
        assertTrue(cliente.getSituacao());

        assertEquals("TELEFONE", cliente.getContatos().getFirst().getTipo());
        assertEquals("6621648176", cliente.getContatos().getFirst().getConteudo());

        assertEquals("TELEFONE", cliente.getContatos().get(1).getTipo());
        assertEquals("9632197043", cliente.getContatos().get(1).getConteudo());

        assertEquals("EMAIL", cliente.getContatos().get(2).getTipo());
        assertEquals("maria_correia8@hotmail.com", cliente.getContatos().get(2).getConteudo());

        assertEquals("EMAIL", cliente.getContatos().get(3).getTipo());
        assertEquals("alissonsodre58@geradornv.com", cliente.getContatos().get(3).getConteudo());
    }

    @Test
    void findById(){
        Clientes cliente = new Clientes();
        cliente.setId(1L);
        cliente.setNome("TEST NUBANK 02");

        when(clientesRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Clientes result = clientesService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("TEST NUBANK 02", result.getNome());
    }

    @Test
    void listarContatos(){
        Clientes cliente = new Clientes();
        cliente.setId(1L);
        cliente.setNome("TEST NUBANK 02");

        List<Contatos> contatos = new ArrayList<>();

        Contatos contato1 = new Contatos();
        contato1.setTipo("TELEFONE");
        contato1.setConteudo("6621648176");
        contato1.setClientes(cliente);

        Contatos contato2 = new Contatos();
        contato2.setTipo("EMAIL");
        contato2.setConteudo("maria_correia8@hotmail.com");
        contato2.setClientes(cliente);

        contatos.add(contato1);
        contatos.add(contato2);

        cliente.setContatos(contatos);

        when(clientesRepository.findById(1L)).thenReturn(Optional.of(cliente));

        List<ContatosResponseDTO> result = clientesService.listarContatos(1L);

        assertNotNull(result);
        assertEquals("TELEFONE",result.getFirst().getTipo());
        assertEquals("6621648176",result.getFirst().getConteudo());

        assertEquals("EMAIL",result.getLast().getTipo());
        assertEquals("maria_correia8@hotmail.com",result.getLast().getConteudo());
    }

    @Test
    void listarTodos() {
        Clientes cliente1 = new Clientes();
        cliente1.setId(1L);
        cliente1.setNome("TEST NUBANK 03 - CLIENTE 1");

        List<Contatos> contatosCliente1 = new ArrayList<>();

        Contatos contato1 = new Contatos();
        contato1.setTipo("TELEFONE");
        contato1.setConteudo("6621648176");
        contato1.setClientes(cliente1);

        contatosCliente1.add(contato1);

        cliente1.setContatos(contatosCliente1);

        List<Contatos> contatosCliente2 = new ArrayList<>();

        Clientes cliente2 = new Clientes();
        cliente2.setId(2L);
        cliente2.setNome("TEST NUBANK 03 - CLIENTE 2");

        Contatos contato2 = new Contatos();
        contato2.setTipo("EMAIL");
        contato2.setConteudo("maria_correia8@hotmail.com");
        contato2.setClientes(cliente2);

        contatosCliente2.add(contato2);

        cliente2.setContatos(contatosCliente2);

        List<Clientes> clientes = new ArrayList<>();

        clientes.add(cliente1);
        clientes.add(cliente2);

        when(clientesRepository.findAll()).thenReturn(clientes);

        List<ClientesResponseDTO> result = clientesService.listarTodos();

        assertNotNull(result);
        assertEquals("TEST NUBANK 03 - CLIENTE 1",result.getFirst().getNome());

        assertEquals("TELEFONE",result.getFirst().getContatos().getFirst().getTipo());
        assertEquals("6621648176",result.getFirst().getContatos().getFirst().getConteudo());

        assertEquals("TEST NUBANK 03 - CLIENTE 2",result.getLast().getNome());

        assertEquals("EMAIL",result.getLast().getContatos().getFirst().getTipo());
        assertEquals("maria_correia8@hotmail.com",result.getLast().getContatos().getFirst().getConteudo());
    }

}
