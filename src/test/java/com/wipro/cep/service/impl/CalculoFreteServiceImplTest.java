package com.wipro.cep.service.impl;

import com.wipro.cep.exception.messages.Messages;
import com.wipro.cep.service.CalculoFreteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculoFreteServiceImplTest {

    @InjectMocks
    private CalculoFreteServiceImpl calculoFreteServiceImpl;

    @Test
    void getValorFreteSuccessful() {
        BigDecimal valorFrete = calculoFreteServiceImpl.getValorFrete("Centro-Oeste");
        assertNotNull(valorFrete);
    }

    @Test
    void getValorFreteError() {
        assertThrows(IllegalArgumentException.class,
                () -> calculoFreteServiceImpl.getValorFrete("xxx"));

    }
}