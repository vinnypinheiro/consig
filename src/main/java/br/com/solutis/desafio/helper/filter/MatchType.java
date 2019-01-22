package br.com.solutis.desafio.helper.filter;


public enum MatchType {
    EQUALS("Igual", "="),
    NOT_EQUAL("Diferente", "<>"),
    GREATER("Maior", ">"),
    GREATER_EQUAL("Maior ou Igual", ">="),
    LESS("Menor", "<"),
    LESS_EQUAL("Menor ou Igual", "<="),
    BETWEEN("Entre", "between"),
    LIKE("Contendo", "ilike"),
    LIKE_START("Iniciando com", "ilike"),
    LIKE_END("Terminando com", "ilike"),
    IN("Contido", "in"),
    NOT_IN("Não Contido", "not in"),
    NOT_LIKE("Nao Contendo", "not ilike");

    private final String name;
    private final String signal;

    private MatchType(String name, String signal) {
        this.name = name;
        this.signal = signal;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getSignal() {
        return signal;
    }
}
