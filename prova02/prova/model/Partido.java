package prova02.prova.model;

import java.util.*;

public class Partido {
    private final int numero;
    private final String nome;
    private final Map<Cargo, Map<Integer, Candidato>> candidatosPorCargo = new HashMap<>();

    public Partido(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public void addCandidato(Candidato candidato) {
        candidatosPorCargo
            .computeIfAbsent(candidato.getCargo(), c -> new HashMap<>())
            .put(candidato.getNumero(), candidato);
    }

    public Candidato getCandidato(Cargo cargo, int numero) {
        return Optional.ofNullable(candidatosPorCargo.get(cargo))
                       .map(map -> map.get(numero))
                       .orElse(null);
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public Collection<Candidato> getCandidatos(Cargo cargo) {
        return candidatosPorCargo.getOrDefault(cargo, Map.of()).values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partido)) return false;
        Partido partido = (Partido) o;
        return numero == partido.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}