package com.wipro.cep.enums;

import java.math.BigDecimal;

public enum FreteRegiaoEnum {

    NORTE("Norte", new BigDecimal(20.83)),
    NORDESTE("Nordeste", new BigDecimal(15.98)),
    SUL("Sul", new BigDecimal(17.30)),

    SUDESTE("Sudeste", new BigDecimal(7.85)),
    CENTROOESTE("Centro-Oeste", new BigDecimal(12.50));

    private final String regiao;
    private final BigDecimal frete;


    FreteRegiaoEnum(String regiao, BigDecimal frete) {
        this.regiao = regiao;
        this.frete = frete;
    }


    public static BigDecimal getFreteBYRegiao(final String regiao) {
        for (final FreteRegiaoEnum uf : FreteRegiaoEnum.values()) {
            if (uf.regiao.equalsIgnoreCase(regiao)) {
                return uf.frete;
            }
        }

        throw new IllegalArgumentException(regiao);
    }
}
