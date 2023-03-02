package com.wipro.cep.service.impl;

import com.wipro.cep.enums.FreteRegiaoEnum;
import com.wipro.cep.service.CalculoFreteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculoFreteServiceImpl implements CalculoFreteService {

    public BigDecimal getValorFrete(String regiao){
        return FreteRegiaoEnum.getFreteBYRegiao(regiao);
    }

}
