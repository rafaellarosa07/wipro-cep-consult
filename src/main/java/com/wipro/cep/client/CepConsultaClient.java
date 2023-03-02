package com.wipro.cep.client;

import com.wipro.cep.client.model.ResponseCepConsulta;
import com.wipro.cep.configuration.ClientsConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CepConsultaClient {

    private final RestTemplate restTemplate;

    private final ClientsConfigurationProperties clientsConfigurationProperties;

    public ResponseEntity<ResponseCepConsulta> getEndereco(String cep) {
        return restTemplate.exchange(
                clientsConfigurationProperties.getApiHost()+"/" + cep + "/json",
                HttpMethod.GET,
                null,
                ResponseCepConsulta.class);
    }

}
