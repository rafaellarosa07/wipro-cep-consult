package com.wipro.cep.service.impl;

import com.wipro.cep.client.CepConsultaClient;
import com.wipro.cep.client.model.ResponseCepConsulta;
import com.wipro.cep.dto.RequestCepDto;
import com.wipro.cep.dto.ResponseEnderecoDto;
import com.wipro.cep.enums.EstadoRegiaoEnum;
import com.wipro.cep.exception.ApiException;
import com.wipro.cep.exception.messages.Messages;
import com.wipro.cep.service.CalculoFreteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ConsultaEnderecoServiceImplTest {

    @InjectMocks
    private ConsultaEnderecoServiceImpl consultaEnderecoServiceImpl;
    @Mock
    private CalculoFreteService calculoFreteService;
    @Mock
    private CepConsultaClient cepConsultaClient;
    @Mock
    private ModelMapper mapper;
    private RequestCepDto requestCepDto;

    private ResponseCepConsulta consult;

    private ResponseEnderecoDto responseEnderecoDto;

    @BeforeEach
    void setUp() {
        requestCepDto = new RequestCepDto();
        requestCepDto.setCep("70640028");

        consult = new ResponseCepConsulta();
        consult.setCep("70640028");
        consult.setUf("DF");

        responseEnderecoDto = new ResponseEnderecoDto();
        responseEnderecoDto.setCep("70640028");
    }


    @Test
    void consultarSuccessful() {
        when(cepConsultaClient.getEndereco(requestCepDto.getCep()))
                .thenReturn(ResponseEntity.of(Optional.of(consult)));
        when(mapper.map(any(), eq(ResponseEnderecoDto.class))).thenReturn(responseEnderecoDto);

        when(calculoFreteService.getValorFrete(EstadoRegiaoEnum.getRegiaoBySigla(consult.getUf())))
                .thenReturn(new BigDecimal(12.5));

        responseEnderecoDto = consultaEnderecoServiceImpl.consultar(requestCepDto);

        assertNotNull(responseEnderecoDto);
        verify(cepConsultaClient).getEndereco(requestCepDto.getCep());
        verify(calculoFreteService).getValorFrete(EstadoRegiaoEnum.getRegiaoBySigla(consult.getUf()));
    }

    @Test
    void consultarCEPInvalid() {
        requestCepDto.setCep("706400288");
        when(cepConsultaClient.getEndereco(requestCepDto.getCep())).thenThrow(HttpClientErrorException.BadRequest.class);

        Exception exception = assertThrows(Exception.class,
                () -> consultaEnderecoServiceImpl.consultar(requestCepDto));

        assertEquals(Messages.CEP_INVALIDO, exception.getMessage());
    }

    @Test
    void consultarCEPNaoExistente() {
        consult.setCep(null);
        when(cepConsultaClient.getEndereco(requestCepDto.getCep()))
                .thenReturn(ResponseEntity.of(Optional.of(consult)));

        ApiException exception = assertThrows(ApiException.class,
                () -> consultaEnderecoServiceImpl.consultar(requestCepDto));

        assertEquals(Messages.CEP_NAO_ENCONTRADO, exception.getMessage());
    }
}