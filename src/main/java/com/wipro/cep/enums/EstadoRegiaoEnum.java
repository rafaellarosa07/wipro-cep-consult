package com.wipro.cep.enums;

public enum EstadoRegiaoEnum {

    AMAZONAS("Amazonas", "AM", "Manaus", "Norte"),
    ALAGOAS("Alagoas", "AL", "Maceió", "Nordeste"),
    ACRE("Acre", "AC", "Rio Branco", "Norte"),
    AMAPA("Amapá", "AP", "Macapá", "Norte"),
    BAHIA("Bahia", "BA", "Salvador", "Nordeste"),
    PARA("Pará", "PA", "Belém", "Norte"),
    MATO_GROSSO("Mato Grosso", "MT", "Cuiabá", "Centro-Oeste"),
    MINAS_GERAIS("Minas Gerais", "MG", "Belo Horizonte", "Centro-Oeste"),
    MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS", "Campo Grande", "Centro-Oeste"),
    GOIAS("Goiás", "GO", "Goiânia", "Centro-Oeste"),
    MARANHAO("Maranhão", "MA", "São Luís", "Nordeste"),
    RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS", "Porto Alegre", "Sul"),
    TOCANTINS("Tocantins", "TO", "Palmas", "Norte"),
    PIAUI("Piauí", "PI", "Teresina", "Nordeste"),
    SAO_PAULO("São Paulo", "SP", "São Paulo", "Sudeste"),
    RONDONIA("Rondônia", "RO", "Porto Velho", "Norte"),
    RORAIMA("Roraima", "RR", "Boa Vista", "Norte"),
    PARANA("Paraná", "PR", "Curitiba", "Sul"),
    CEARA("Ceará", "CE", "Fortaleza", "Nordeste"),
    PERNAMBUCO("Pernambuco", "PE", "Recife", "Nordeste"),
    SANTA_CATARINA("Santa Catarina", "SC", "Florianópolis", "Sul"),
    PARAIBA("Paraíba", "PB", "João Pessoa", "Nordeste"),
    RIO_GRANDE_DO_NORTE("Rio Grande do Norte", "RN", "Natal", "Nordeste"),
    ESPIRITO_SANTO("Espírito Santo", "ES", "Vitória", "Sudeste"),
    RIO_DE_JANEIRO("Rio de Janeiro", "RJ", "Rio de Janeiro", "Sudeste"),
    SERGIPE("Sergipe", "SE", "Aracaju", "Nordeste"),
    DISTRITO_FEDERAL("Distrito Federal", "DF", "Brasília", "Centro-Oeste");

    private final String nome;
    private final String sigla;
    private final String capital;
    private final String regiao;

    /**
     * Construtor do enum
     *
     * @param nome    nome da unidade da federação completo
     * @param sigla   sigla da unidade da federação
     * @param capital nome da capital da unidade da federação
     * @param regiao
     */
    EstadoRegiaoEnum(final String nome, final String sigla, final String capital, String regiao) {
        this.nome = nome;
        this.sigla = sigla;
        this.capital = capital;
        this.regiao = regiao;
    }

    /**
     * Converte a partir do nome da Unidade da Federacao, para o respectivo enum.
     *
     * @param nomeUf o nome da Unidade da Federação. Exemplo: "São Paulo"
     * @return o enum da Unidade da Federação
     * @throws IllegalArgumentException caso não ache o enum pelo nome da UF
     */
    public static EstadoRegiaoEnum fromUF(final String nomeUf) {
        for (final EstadoRegiaoEnum uf : EstadoRegiaoEnum.values()) {
            if (uf.nome.equalsIgnoreCase(nomeUf)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(nomeUf);
    }

    /**
     * Converte a partir da Sigla da UF no parâmetro, para o enum da Unidade da Federação.
     *
     * @param sigla da Unidade da Federação. Exemplo: "MG"
     * @return a Unidade da Federação
     * @throws IllegalArgumentException caso a sigla da UF não exista
     */
    public static EstadoRegiaoEnum fromSigla(final String sigla) {
        for (final EstadoRegiaoEnum uf : EstadoRegiaoEnum.values()) {
            if (uf.sigla.equalsIgnoreCase(sigla)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(sigla);
    }

    public static String getRegiaoBySigla(final String sigla) {
        for (final EstadoRegiaoEnum uf : EstadoRegiaoEnum.values()) {
            if (uf.sigla.equalsIgnoreCase(sigla)) {
                return uf.regiao;
            }
        }

        throw new IllegalArgumentException(sigla);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnidadeFederacao{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", sigla='").append(sigla).append('\'');
        sb.append(", capital='").append(capital).append('\'');
        sb.append('}');
        return sb.toString();
    }
}



