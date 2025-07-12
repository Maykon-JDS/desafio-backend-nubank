package br.com.nubank.controller;

import br.com.nubank.dto.Clientes.ClientesRecordDTO;
import br.com.nubank.dto.Clientes.ClientesResponseDTO;
import br.com.nubank.dto.Contatos.ContatosChildResponseDTO;
import br.com.nubank.dto.Contatos.ContatosResponseDTO;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.service.ClientesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClientesControllerTest {

    @Mock
    ClientesService clientesService;

    @InjectMocks
    ClientesController clientesController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(clientesController).build();
    }

    @Test
    void criar() throws Exception {

        Clientes cliente = new Clientes();

        cliente.setId(1L);
        cliente.setNome("TEST NUBANK 01");

        List<Contatos> contatos = new ArrayList<>();

        Contatos contato = new Contatos();
        contato.setId(1L);
        contato.setTipo("TELEFONE");
        contato.setConteudo("6621648176");
        contato.setClientes(cliente);

        contatos.add(contato);

        cliente.setContatos(contatos);

        when(clientesService.salvarCliente(any(ClientesRecordDTO.class))).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .content("{\"nome\":\"TEST NUBANK 01\",\"contatos\":[{\"tipo\":\"TELEFONE\",\"conteudo\":\"6621648176\"}]}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("TEST NUBANK 01"))
                .andExpect(jsonPath("$.contatos").isNotEmpty())
                .andExpect(jsonPath("$.contatos[0].tipo").value("TELEFONE"))
                .andExpect(jsonPath("$.contatos[0].conteudo").value("6621648176"));
    }

    @Test
    void listarTotos() throws Exception {
        List<ContatosChildResponseDTO> contatos1 = new ArrayList<>();

        ContatosChildResponseDTO contato1 = new ContatosChildResponseDTO();
        contato1.setId(1L);
        contato1.setTipo("TELEFONE");
        contato1.setConteudo("6621648176");
        contato1.setSituacao(true);

        contatos1.add(contato1);

        ClientesResponseDTO cliente1 = new ClientesResponseDTO();

        cliente1.setId(1L);
        cliente1.setNome("TEST NUBANK 02 - Cliente 1");
        cliente1.setContatos(contatos1);

        List<ContatosChildResponseDTO> contatos2 = new ArrayList<>();

        ContatosChildResponseDTO contato2 = new ContatosChildResponseDTO();
        contato2.setId(2L);
        contato2.setTipo("EMAIL");
        contato2.setConteudo("maria_correia8@hotmail.com");
        contato2.setSituacao(true);

        contatos2.add(contato2);

        ClientesResponseDTO cliente2 = new ClientesResponseDTO();

        cliente2.setId(2L);
        cliente2.setNome("TEST NUBANK 02 - Cliente 2");
        cliente2.setContatos(contatos2);

        List<ClientesResponseDTO> clientes = new ArrayList<>();

        clientes.add(cliente1);
        clientes.add(cliente2);

        when(clientesService.listarTodos()).thenReturn(clientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("TEST NUBANK 02 - Cliente 1"))
                .andExpect(jsonPath("$[0].contatos").isNotEmpty())
                .andExpect(jsonPath("$[0].contatos[0].id").value(1))
                .andExpect(jsonPath("$[0].contatos[0].tipo").value("TELEFONE"))
                .andExpect(jsonPath("$[0].contatos[0].conteudo").value("6621648176"))
                .andExpect(jsonPath("$[0].contatos[0].situacao").value(true))
                .andExpect(jsonPath("$[1].nome").value("TEST NUBANK 02 - Cliente 2"))
                .andExpect(jsonPath("$[1].contatos").isNotEmpty())
                .andExpect(jsonPath("$[1].contatos[0].id").value(2))
                .andExpect(jsonPath("$[1].contatos[0].tipo").value("EMAIL"))
                .andExpect(jsonPath("$[1].contatos[0].conteudo").value("maria_correia8@hotmail.com"))
                .andExpect(jsonPath("$[1].contatos[0].situacao").value(true));
    }

    @Test
    void listarContatos() throws Exception {
        List<ContatosResponseDTO> contatos = new ArrayList<>();

        ContatosResponseDTO contato1 = new ContatosResponseDTO();
        contato1.setId(1L);
        contato1.setTipo("TELEFONE");
        contato1.setConteudo("6621648176");
        contato1.setSituacao(true);
        contato1.setClienteId(1L);

        ContatosResponseDTO contato2 = new ContatosResponseDTO();
        contato2.setId(2L);
        contato2.setTipo("EMAIL");
        contato2.setConteudo("maria_correia8@hotmail.com");
        contato2.setSituacao(true);
        contato2.setClienteId(1L);

        contatos.add(contato1);
        contatos.add(contato2);

        when(clientesService.listarContatos(1L)).thenReturn(contatos);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{clienteId}/contatos", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipo").value("TELEFONE"))
                .andExpect(jsonPath("$[0].conteudo").value("6621648176"))
                .andExpect(jsonPath("$[0].situacao").value(true))
                .andExpect(jsonPath("$[0].clienteId").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].tipo").value("EMAIL"))
                .andExpect(jsonPath("$[1].conteudo").value("maria_correia8@hotmail.com"))
                .andExpect(jsonPath("$[1].situacao").value(true))
                .andExpect(jsonPath("$[1].clienteId").value(1));
    }
}
