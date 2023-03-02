package com.wipro.cep.controller;

import com.wipro.cep.dto.RequestCepDto;
import com.wipro.cep.service.ConsultaEnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/consulta-endereco")
public class ConsultaEnderecoController {


    private ConsultaEnderecoService consultaEnderecoService;

    @Autowired
    public ConsultaEnderecoController(ConsultaEnderecoService consultaEnderecoService) {
        this.consultaEnderecoService = consultaEnderecoService;
    }


    @PostMapping
    @Operation(
            summary = "Consultar Frete",
            description = "Consultar Frete",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request para Consultar Frete com Sucesso"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> consultar(@RequestBody RequestCepDto requestCepDto) {
        var reservation = consultaEnderecoService.consultar(requestCepDto);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

}
