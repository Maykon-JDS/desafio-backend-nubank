package br.com.nubank.controller;

import br.com.nubank.dto.Contatos.ContatosDTO;
import br.com.nubank.model.Clientes;
import br.com.nubank.model.Contatos;
import br.com.nubank.service.ClientesService;
import br.com.nubank.service.ContatosService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ContatosControllerTest {

    @Mock
    ContatosService contatosService;

    @Mock
    ClientesService clientesService;

    @InjectMocks
    ContatosController contatosController;

    MockMvc mockMvc;

    @BeforeEach
    void  setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(contatosController).build();
    }

    @Test
    void salvarContato() throws Exception {
        Clientes cliente = new Clientes();
        cliente.setId(1L);
        cliente.setNome("TEST NUBANK 01");

        Contatos contato = new Contatos();
        contato.setId(1L);
        contato.setTipo("EMAIL");
        contato.setConteudo("maria_correia8@hotmail.com");
        contato.setSituacao(true);
        contato.setClientes(cliente);

        when(clientesService.findById(1L)).thenReturn(cliente);
        when(contatosService.salvarContato(any(ContatosDTO.class))).thenReturn(contato);

        mockMvc.perform(MockMvcRequestBuilders.post("/contatos")
                        .content("{\"clienteId\":\"1\",\"tipo\":\"EMAIL\",\"conteudo\":\"maria_correia8@hotmail.com\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(contato.getId()))
                .andExpect(jsonPath("$.tipo").value(contato.getTipo()))
                .andExpect(jsonPath("$.conteudo").value(contato.getConteudo()))
                .andExpect(jsonPath("$.situacao").value(contato.getSituacao()))
                .andExpect(jsonPath("$.clienteId").value(cliente.getId()));

    }

}
