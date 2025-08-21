package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.contas.ContaDTOFull;
import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContaController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
        ContaController.class
})
public class ContaControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ContaRepository contaRepository;

    @Before
    public void setup(){
        this.objectMapper = new ObjectMapper();
        this.contaRepository = Mockito.mock(ContaRepository.class);
        ContaController restController = new ContaController(contaRepository);
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }


    @Test
    public void listarTodos() throws Exception {

        ContaDTOFull contaDTOFull = new ContaDTOFull("1");
        Conta contaExpected = new Conta(1, 1, "1000,00", "1", "1", 1, 1);
        List<Conta> contaList = new ArrayList<>();
        contaList.add(contaExpected);
        Conta contaRetorno = new Conta(1,
                1, "1000,00","1","1",1,1);
        List<Conta> contaRetornoList = new ArrayList<>();
        contaRetornoList.add(contaRetorno);
        when(this.contaRepository.findAll(any())).thenReturn(contaRetornoList);
                this.mockMvc.perform(post("/listarConta").content(this.objectMapper.writeValueAsString(contaDTOFull))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(this.objectMapper.writeValueAsString(contaList)));
    }

}
