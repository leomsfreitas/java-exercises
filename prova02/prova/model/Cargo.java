package prova02.prova.model;

public enum Cargo {
    DEPUTADO_FEDERAL,
    DEPUTADO_ESTADUAL,
    SENADOR,
    GOVERNADOR,
    PRESIDENTE;

    public static Cargo fromDescricao(String descricao) {
        return switch (descricao.toUpperCase()) {
            case "DEPUTADO ESTADUAL" -> DEPUTADO_ESTADUAL;
            case "DEPUTADO FEDERAL" -> DEPUTADO_FEDERAL;
            case "SENADOR" -> SENADOR;
            case "GOVERNADOR" -> GOVERNADOR;
            case "PRESIDENTE" -> PRESIDENTE;
            default -> throw new IllegalArgumentException("Cargo inválido: " + descricao);
        };
    }
}