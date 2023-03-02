package com.wipro.cep.service.impl;

import com.wipro.cep.client.CepConsultaClient;
import com.wipro.cep.client.model.ResponseCepConsulta;
import com.wipro.cep.dto.RequestCepDto;
import com.wipro.cep.dto.ResponseEnderecoDto;
import com.wipro.cep.enums.EstadoRegiaoEnum;
import com.wipro.cep.exception.ApiException;
import com.wipro.cep.exception.messages.Messages;
import com.wipro.cep.service.CalculoFreteService;
import com.wipro.cep.service.ConsultaEnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConsultaEnderecoServiceImpl implements ConsultaEnderecoService {


    private final CepConsultaClient cepConsultaClient;
    private ModelMapper mapper;

    private final CalculoFreteService calculoFreteService;

    public ConsultaEnderecoServiceImpl(CepConsultaClient cepConsultaClient, ModelMapper mapper,
                                       CalculoFreteService calculoFreteService) {
        this.cepConsultaClient = cepConsultaClient;
        this.mapper = mapper;
        this.calculoFreteService = calculoFreteService;
    }

    @Override
    public ResponseEnderecoDto consultar(RequestCepDto requestCepDto) {
        try {
            ResponseCepConsulta consult = cepConsultaClient.getEndereco(
                    requestCepDto.getCep().replace("-", "")).getBody();
            verificarCepExistente(consult);
            return preencherResponse(consult);
        } catch (ApiException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.CEP_INVALIDO);
        }
    }

    private void verificarCepExistente(ResponseCepConsulta consult) {
        if (Objects.isNull(consult.getCep())) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.CEP_NAO_ENCONTRADO);
        }
    }

    private ResponseEnderecoDto preencherResponse(ResponseCepConsulta consult) {
        ResponseEnderecoDto responseEndereco = mapper.map(consult
                , ResponseEnderecoDto.class);
        responseEndereco.setFrete(calculoFreteService.getValorFrete(
                EstadoRegiaoEnum.getRegiaoBySigla(consult.getUf())));
        responseEndereco.setCidade(consult.getLocalidade());
        responseEndereco.setEstado(consult.getUf());
        responseEndereco.setRua(consult.getLogradouro());

        return responseEndereco;
    }
}
