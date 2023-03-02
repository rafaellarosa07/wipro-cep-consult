package com.wipro.cep.service;

import com.wipro.cep.client.model.ResponseCepConsulta;
import com.wipro.cep.dto.RequestCepDto;
import com.wipro.cep.dto.ResponseEnderecoDto;
import org.springframework.http.ResponseEntity;

public interface ConsultaEnderecoService {

    ResponseEnderecoDto consultar(RequestCepDto requestCepDto);

}
