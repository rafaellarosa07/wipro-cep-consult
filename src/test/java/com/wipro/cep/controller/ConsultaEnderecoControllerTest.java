package com.wipro.cep.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.cep.configuration.ApplicationConfig;
import com.wipro.cep.dto.RequestCepDto;
import com.wipro.cep.dto.ResponseEnderecoDto;
import com.wipro.cep.service.ConsultaEnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
class ConsultaEnderecoControllerTest {

    private static final String PATH = "/consulta-endereco";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsultaEnderecoService consultaEnderecoService;

    private RequestCepDto requestCepDto;

    private ResponseEnderecoDto responseEnderecoDto;

    @BeforeEach
    void setUp() {
        requestCepDto = new RequestCepDto("70640028");
        responseEnderecoDto = new ResponseEnderecoDto();
    }

    @Test
    void consultar() throws Exception {
        when(consultaEnderecoService.consultar(requestCepDto)).thenReturn(responseEnderecoDto);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(responseEnderecoDto)))
                .andExpect(status().isCreated());
    }
}